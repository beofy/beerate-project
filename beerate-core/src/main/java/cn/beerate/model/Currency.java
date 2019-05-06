package cn.beerate.model;

/**
 * 币种
 */
public enum Currency {

    RMB("人名币"),


    MY("美元");

    private String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
