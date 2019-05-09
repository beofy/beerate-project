package cn.beerate.model;

/**
 * 流通股性质
 */
public enum StockNature {

    NONE("请选择"),

    CIRCULATE("流通"),

    RESTRICTED("限售"),

    PROHIBITION("禁售");

    private String value;

    StockNature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
