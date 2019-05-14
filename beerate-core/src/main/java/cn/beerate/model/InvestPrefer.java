package cn.beerate.model;

/**
 * 投资偏好
 */
public enum InvestPrefer {

    NONE("请选择"),

    STOCK_TRANSFER("老股转让"),

    PRE_IPO("PRE-IPO"),

    BLOCK_TRADE("大宗交易"),

    STOCK_PLEDGE("股票质押"),

    ITEM_LOAN("项目融资");

    private String value;

    InvestPrefer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
