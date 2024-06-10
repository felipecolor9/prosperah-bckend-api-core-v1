package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model;

import br.com.prosperah.api.appcore.domain.FinancialMovement;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static br.com.prosperah.api.appcore.utils.ConvertUtils.ToBytes;
import static java.util.UUID.randomUUID;

@Data
@Builder
@Entity
@Table(name = "tb201_crtr_econ_mov_fin")
public class FinancialMovementPersistData {

    @Id
    @Column(name = "cod_mov_fin")
    private byte[] id;

    @Column(name = "cod_crtr_fk")
    private byte[] walletId;

    @Column(name = "cod_dono_usr_fk")
    private byte[] ownerId;

    @Column(name = "mov_fin_val_brl")
    private double applyValueBRL;

    @Column(name = "cod_enum_corr_fin")
    private int brokerCode;

    @Column(name = "cod_enum_tipo_mov_fin")
    private int walletOperationtypeCode;

    @Column(name = "mov_fin_qntd_unit_ativ")
    private double FinMovementAmount;

    @Column(name = "mov_fin_data")
    private Timestamp operationDate;

    public static FinancialMovementPersistData toPersistData(FinancialMovement financialMovement) {
        return FinancialMovementPersistData.builder()
                .id(ToBytes(randomUUID()))
                .walletId(financialMovement.getWalletId())
                .ownerId(financialMovement.getOwnerId())
                .applyValueBRL(financialMovement.getApplyValueBRL())
                .brokerCode(financialMovement.getBrokerCode())
                .walletOperationtypeCode(financialMovement.getWalletOperationCode())
                .FinMovementAmount(financialMovement.getFinMovementAmount())
                .operationDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
}
