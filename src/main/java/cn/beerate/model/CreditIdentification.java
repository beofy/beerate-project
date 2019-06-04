package cn.beerate.model;

/**
 * 增信身份
 */
public enum CreditIdentification {

    NONE("请选择"),

    STRONGER_STOCK_HOLDER("大股东/实控人"),

    OTHER_STOCK_HOLDER("其他股东"),

    GUARANTEE("第三方担保"),

    OTHER("其他");

    private String value;

    CreditIdentification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
