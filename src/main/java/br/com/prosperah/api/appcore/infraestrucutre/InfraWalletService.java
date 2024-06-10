package br.com.prosperah.api.appcore.infraestrucutre;

import br.com.prosperah.api.appcore.domain.FinancialMovement;
import br.com.prosperah.api.appcore.domain.Wallet;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.exceptions.InvalidOperationException;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.WalletDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.prosperah.api.appcore.domain.Wallet.toWallet;

@Service

public class InfraWalletService {

    @Autowired
    WalletDatasourceService walletDatasource;

    public ResponseEntity<Wallet> updateWallet(FinancialMovement movement, String userId) throws InvalidOperationException {
        return new ResponseEntity<>(toWallet(walletDatasource.registerFinancialMovement(movement, userId)),
                "Movimentação registrada com sucesso", 201);
    }
}

