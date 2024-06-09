package br.com.prosperah.api.appcore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class WalletApply {

    @JsonIgnore
    private byte[] codMovFin;

    @JsonIgnore
    private byte[] walletId;

    @JsonIgnore
    private byte[] ownerId;

    private float applyValueBRL;

    @JsonIgnore
    private int brokerCode; //Enum

    private char walletOperationtypeCode; //Enum
    private float FinMovementAmount;
    private Timestamp operationDate;
}
