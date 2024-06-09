package br.com.prosperah.api.appcore.domain;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.WalletPersistData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class Wallet {

    @JsonIgnore
    private byte[] userId;

    private double fixedAssetsPatrimony;

    private double variableAssetsPatrimony;

    private double fixedAssetsRentability;

    private double variableAssetsRentability;

    private Timestamp creationDate;

    public static Wallet toWallet(WalletPersistData persistData) {
        return Wallet.builder()
                .userId(persistData.getUserId())
                .fixedAssetsPatrimony(persistData.getFixedAssetsPatrimony())
                .variableAssetsPatrimony(persistData.getVariableAssetsPatrimony())
                .fixedAssetsRentability(persistData.getFixedAssetsRentability())
                .variableAssetsRentability(persistData.getVariableAssetsRentability())
                .creationDate(persistData.getCreationDate())
                .build();
    }
}
