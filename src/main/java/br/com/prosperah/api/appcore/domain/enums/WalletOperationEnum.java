package br.com.prosperah.api.appcore.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum WalletOperationEnum {

    BUY(1),

    SELL(2),

    EDIT(3);

    private final int value;

    private static final Map<Integer, BrokerEnum> ENUM_MAP = new HashMap<>();

    static {
        for (BrokerEnum brokerEnum : BrokerEnum.values()) {
            ENUM_MAP.put(brokerEnum.getValue(), brokerEnum);
        }
    }

    public static BrokerEnum valueOf(int value) {
        return ENUM_MAP.get(value);
    }
}
