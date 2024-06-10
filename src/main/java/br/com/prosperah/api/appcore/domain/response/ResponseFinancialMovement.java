package br.com.prosperah.api.appcore.domain.response;

import br.com.prosperah.api.appcore.domain.FinancialMovement;
import br.com.prosperah.api.appcore.domain.enums.BrokerEnum;
import br.com.prosperah.api.appcore.domain.enums.WalletOperationEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class ResponseFinancialMovement {

    @JsonIgnore
    private byte[] id;

    @JsonIgnore
    private byte[] walletId;

    @JsonIgnore
    private byte[] ownerId;

    private float applyValueBRL;

    private String brokerName;

    private String walletOperationType;

    private float FinMovementAmount;

    private Timestamp operationDate;

    public static ResponseFinancialMovement toResponseFinancialMovement(FinancialMovement financialMovement, int brokerCode, int operationTypeCode) {
        {
            return ResponseFinancialMovement
                    .builder()
                    .id(financialMovement.getId())
                    .walletId(financialMovement.getWalletId())
                    .ownerId(financialMovement.getOwnerId())
                    .applyValueBRL(financialMovement.getApplyValueBRL())
                    .brokerName(BrokerEnum.valueOf(brokerCode).name())
                    .walletOperationType(WalletOperationEnum.valueOf(operationTypeCode).name())
                    .FinMovementAmount(financialMovement.getFinMovementAmount())
                    .operationDate(financialMovement.getOperationDate())
                    .build();
        }
    }
}
