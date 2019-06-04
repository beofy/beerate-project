package cn.beerate.model;

/**
 * 金额单位
 */
public enum AmountUnit{

    NONE("请选择"),

    Y("元"),

    WY("万元"),

    YY("亿元");

    private String value;

    AmountUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
