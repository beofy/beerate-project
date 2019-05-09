package cn.beerate.model;

/**
 * 股票板块
 */
public enum StockBlock {

    NONE("请选择"),

    HS300("沪深300成分股"),

    OTHER_MAIN_STOCK_BOARD("主板其他"),

    MIDDLE_SMALL_STOCK_BOARD("中小板"),

    ENTERPRISE_BOARD("创业板");

    private String value;

    StockBlock(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
