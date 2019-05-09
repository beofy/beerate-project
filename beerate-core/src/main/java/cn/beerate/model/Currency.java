package cn.beerate.model;

/**
 * 币种
 */
public enum Currency {

    NONE("请选择"),

    RMB("人民币"),


    MY("美元");

    private String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
