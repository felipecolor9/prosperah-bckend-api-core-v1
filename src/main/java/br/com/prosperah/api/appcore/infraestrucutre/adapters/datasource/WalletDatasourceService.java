package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.domain.Wallet;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.WalletPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository.WalletRepository;
import br.com.prosperah.api.appcore.utils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static br.com.prosperah.api.appcore.utils.ConvertUtils.toUUID;
import static java.util.UUID.randomUUID;

@Service
public class WalletDatasourceService {

    private static final Logger log = LoggerFactory.getLogger(WalletDatasourceService.class);

    @Autowired
    WalletRepository walletRepository;

    public void createWallet(byte[] userId) {

        if (walletRepository.findByUserId(userId).isEmpty()) {

            log.info("Creating wallet...");
            walletRepository.save(initializeWallet(userId));

            log.info("Wallet created with success");
        } else log.error("internal error - wallet already exists!");
    }

    public Optional<WalletPersistData> recreateWallet(byte[] userId) {
        log.info("Recriando carteira para o usuário: {}", toUUID(userId));
        return Optional.of(walletRepository.save(initializeWallet(userId)));
    }

    public Optional<Wallet> loadWallet(byte[] userId) {
        return walletRepository.findByUserId(userId).map(Wallet::toWallet);
    }

    private WalletPersistData initializeWallet(byte[] userId) {
        return WalletPersistData.builder()
                .id(ConvertUtils.ToBytes(randomUUID()))
                .userId(userId)
                .fixedAssetsRentability(0.0)
                .fixedAssetsPatrimony(0.0)
                .variableAssetsRentability(0.0)
                .variableAssetsPatrimony(0.0)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

}
