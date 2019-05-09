package cn.beerate.model;

/**
 * 净利润
 */
public enum  NetProfit {

    NONE("请选择"),

    LESS_THAN_500_MILLION("少于500万"),

    IN_500_TO_1000_MILLION("500-1000万"),

    IN_1000_TO_2000_MILLION("1000-2000万"),

    OVER_1000_TO_2000_MILLION("2000万以上");

    private String value;

    NetProfit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
