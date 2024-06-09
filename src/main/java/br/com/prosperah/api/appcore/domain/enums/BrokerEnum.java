package br.com.prosperah.api.appcore.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum BrokerEnum {

    AGORA_CTVM_SA(0),
    ATIVA_INVESTIMENTOS_SA_CTCV(1),
    BANCO_ABN_AMRO_SA(2),
    BANRISUL_SA_CVMC(3),
    BB_BANCO_DE_INVESTIMENTO_SA(4),
    BGC_LIQUIDEZ_DTVM(5),
    BRADESCO_SA_CTVM(6),
    BTG_PACTUAL_CTVM_SA(7),
    C6_CTVM_LTDA(8),
    CITIGROUP_GMB_CCTVM_SA(9),
    CLEAR_CORRETORA_GRUPO_XP(10),
    CM_CAPITAL_MARKETS_CCTVM_LTDA(11),
    CREDIT_SUISSE_BRASIL_SA_CTVM(12),
    GENIAL_INSTITUCIONAL_CCTVM_SA(13),
    GENIAL_INVESTIMENTOS_CVM_SA(14),
    GOLDMAN_SACHS_DO_BRASIL_CTVM(15),
    GUIDE_INVESTIMENTOS_SA_CV(16),
    H_COMMCOR_DTVM_LTDA(17),
    ICAP_DO_BRASIL_CTVM_LTDA(18),
    IDEAL_CTVM_SA(19),
    INTER_DTVM_LTDA(20),
    ITAU_CV_SA(21),
    JP_MORGAN_CCVM_SA(22),
    LEV_DTVM(23),
    MERC_DO_BRASIL_COR_SA_CTVM(24),
    MERRILL_LYNCH_SA_CTVM(25),
    MIRAE_ASSET_WEALTH_MANAGEMENT_BRASIL_CCTVM_LTDA(26),
    MODAL_DTVM_LTDA(27),
    MORGAN_STANLEY_CTVM_SA(28),
    NECTON_INVESTIMENTOS_SA_CVMC(29),
    NOVA_FUTURA_CTVM_LTDA(30),
    NU_INVEST_CORRETORA_DE_VALORES_SA(31),
    ORAMA_DTVM_SA(32),
    PLANNER_CV_SA(33),
    RB_CAPITAL_DTVM_LTDA(34),
    RENASCENCA_DTVM_LTDA(35),
    SAFRA_CVC_LTDA(36),
    SANTANDER_CCVM_SA(37),
    SCOTIABANK_BRASIL_SA_CTVM(38),
    STONEX_DISTRIBUIDORA_DE_TITULOS_E_VALORES_MOBILIARIOS_LTDA(39),
    TERRA_INVESTIMENTOS_DTVM_LTDA(40),
    TORO_CVTM_LTDA(41),
    TULLETT_PREBON_BRASIL_CVC_LTDA(42),
    UBS_BRASIL_CCTVM_SA(43),
    VOTORANTIM_CTVM_LTDA(44),
    XP_INVESTIMENTOS_CCTVM_SA(45);

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
