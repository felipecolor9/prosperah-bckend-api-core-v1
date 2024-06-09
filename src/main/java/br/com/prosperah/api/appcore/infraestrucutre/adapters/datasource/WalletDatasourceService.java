package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.WalletPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository.WalletRepository;
import br.com.prosperah.api.appcore.utils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WalletDatasourceService {

    private static final Logger log = LoggerFactory.getLogger(WalletDatasourceService.class);

    @Autowired
    WalletRepository walletRepository;

    public void createWallet(byte[] userId) {

        if (walletRepository.findById(userId).isEmpty()) {

            log.info("Creating wallet...");
            walletRepository.save(initializeWallet(userId));

            log.info("Wallet created with success");
        } else log.error("internal error - wallet already exists!");
    }

    private WalletPersistData initializeWallet(byte[] userId) {
        return WalletPersistData.builder()
                .id(ConvertUtils.ToBytes(UUID.randomUUID()))
                .userId(userId)
                .fixedAssetsRentability(0.0)
                .fixedAssetsPatrimony(0.0)
                .variableAssetsRentability(0.0)
                .variableAssetsPatrimony(0.0)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

}
