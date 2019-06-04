package cn.beerate.model;

/**
 * 对赌条件
 */
public enum RatchetTerms {

    NONE("请选择"),

    NO_BET("无对赌"),

    REPURCHASE("回购对赌"),

    PERFORMANCE("业绩对赌");

    private String value;

    RatchetTerms(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
