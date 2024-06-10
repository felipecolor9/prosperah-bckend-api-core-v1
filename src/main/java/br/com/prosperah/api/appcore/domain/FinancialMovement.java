package br.com.prosperah.api.appcore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialMovement {

    @JsonIgnore
    private byte[] id;

    @JsonIgnore
    private byte[] walletId;

    @JsonIgnore
    private byte[] ownerId;

    private float applyValueBRL;

    private int brokerCode;

    private int walletOperationCode;

    private float finMovementAmount;

    private Timestamp operationDate;
}
