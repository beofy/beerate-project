package cn.beerate.model;

/**
 * 周期单位
 */
public enum PeriodUnit {

    DAY("天"),

    MONTH("月"),

    YEAR("年");

    private String value;

    PeriodUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
