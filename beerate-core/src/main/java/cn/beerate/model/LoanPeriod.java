package cn.beerate.model;

/**
 * 借款周期
 */
public enum  LoanPeriod {

    ONE_YEAR("1年"),

    ONE_TO_THREE_YEAR("1-3年"),

    THREE_TO_FIVE_YEAR("3-5年"),

    OVER_FIVE_YEAR("5年以上");

    private String value;

    LoanPeriod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
