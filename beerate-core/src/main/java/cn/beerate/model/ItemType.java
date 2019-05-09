package cn.beerate.model;

/**
 * 项目类型
 */
public enum ItemType {

    NONE("请选择"),

    STOCK_TRANSFER("老股转让"),

    PRE_IPO("老股转让"),

    BLOCK_TRADE("老股转让"),

    STOCK_PLEDGE("老股转让");

    private String value;

    ItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
