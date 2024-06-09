package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model;

import br.com.prosperah.api.appcore.domain.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb301_usr_crtr_econ")
public class WalletPersistData {

    @Id
    @Column(name = "cod_crtr")
    private byte[] id;

    @Column(name = "cod_dono_usr_fk")
    private byte[] userId;

    @Column(name = "crtr_econ_patr_fixo")
    private double fixedAssetsPatrimony;

    @Column(name = "crtr_econ_patr_var")
    private double variableAssetsPatrimony;

    @Column(name = "crtr_econ_rent_fixo")
    private double fixedAssetsRentability;

    @Column(name = "crtr_econ_rent_var")
    private double variableAssetsRentability;

    @Column(name = "crtr_econ_data_criacao")
    private Timestamp creationDate;

    public static WalletPersistData toPersistData(Wallet wallet) {
        return WalletPersistData.builder()
                .userId(wallet.getUserId())
                .fixedAssetsPatrimony(wallet.getFixedAssetsPatrimony())
                .variableAssetsPatrimony(wallet.getVariableAssetsPatrimony())
                .fixedAssetsRentability(wallet.getFixedAssetsRentability())
                .variableAssetsRentability(wallet.getVariableAssetsRentability())
                .creationDate(Timestamp.valueOf(wallet.getCreationDate().toLocalDateTime()))
                .build();
    }
}


