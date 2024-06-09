package br.com.prosperah.api.appcore.infraestrucutre;

import br.com.prosperah.api.appcore.domain.Wallet;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.WalletDatasourceService;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.WalletPersistData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class InfraWalletService {

    @Autowired
    WalletDatasourceService walletDatasource;

    public ResponseEntity<Wallet> updateWallet(Wallet wallet) {
        return new ResponseEntity<>(walletDatasource.updateWallet(WalletPersistData.toPersistData(wallet)),
                "Carteira atualizada com sucesso!", 200);
    }
}
