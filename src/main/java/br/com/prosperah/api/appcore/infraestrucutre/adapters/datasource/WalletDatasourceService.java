package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.domain.FinancialMovement;
import br.com.prosperah.api.appcore.domain.Wallet;
import br.com.prosperah.api.appcore.exceptions.InvalidOperationException;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.FinancialMovementPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.WalletPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository.FinancialMovementRepository;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository.WalletRepository;
import br.com.prosperah.api.appcore.utils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.FinancialMovementPersistData.toPersistData;
import static br.com.prosperah.api.appcore.utils.ConvertUtils.ToBytes;
import static br.com.prosperah.api.appcore.utils.ConvertUtils.toUUID;
import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;

@Service
public class WalletDatasourceService {

    private static final Logger log = LoggerFactory.getLogger(WalletDatasourceService.class);

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    FinancialMovementRepository finMovementRepository;

    public Optional<WalletPersistData> recreateWallet(byte[] userId) {
        log.warn("Carteira não encontrada! Recriando carteira para o usuário: {}", toUUID(userId));
        return Optional.of(walletRepository.save(initializeWallet(userId)));
    }

    public Optional<WalletPersistData> loadWallet(byte[] userId) {
        return walletRepository.findByUserId(userId);
    }

    private WalletPersistData initializeWallet(byte[] userId) {
        return WalletPersistData.builder()
                .id(ToBytes(randomUUID()))
                .userId(userId)
                .fixedAssetsRentability(0.0)
                .fixedAssetsPatrimony(0.0)
                .variableAssetsRentability(0.0)
                .variableAssetsPatrimony(0.0)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    public WalletPersistData registerFinancialMovement(FinancialMovement movement, String userId) throws InvalidOperationException {
        var foundWallet = loadWallet(ToBytes(fromString(userId)));

        if (foundWallet.isEmpty()) {
            log.warn("Carteira não encontrada! Recriando carteira para o usuário: {}", userId);
            return initializeWallet(ToBytes(fromString(userId)));
        }

        movement.setWalletId(foundWallet.get().getId());
        movement.setId(ConvertUtils.ToBytes(randomUUID()));
        movement.setOwnerId(ToBytes(fromString(userId)));
        movement.setOperationDate(Timestamp.valueOf(LocalDateTime.now()));

        var savedMovement = finMovementRepository.save(toPersistData(movement));
        updateWalletPatrimony(savedMovement.getApplyValueBRL(), savedMovement.getWalletOperationtypeCode(), foundWallet.get());

        log.info("Movimentação da carteira [] registrada com sucesso", savedMovement.getWalletId());
    return foundWallet.get();
    }

    private void updateWalletPatrimony(double valueBRL, int operation, WalletPersistData wallet) throws InvalidOperationException {

        if (operation == 1) wallet.setFixedAssetsPatrimony(wallet.getFixedAssetsPatrimony() + valueBRL);
        else if (operation == 2) wallet.setFixedAssetsPatrimony(wallet.getFixedAssetsPatrimony() - valueBRL);
        else throw new InvalidOperationException();

        walletRepository.updateWalletPatrimony(wallet.getId(), wallet.getFixedAssetsPatrimony());
    }
}
