package cn.beerate.model;

/**
 * 减持方身份
 */
public enum UnderWeightIdentification {

    NONE("请选择"),

    STRONGER_STOCK_HOLDER("大股东/实控人"),

    OTHER_STOCK_HOLDER("其他股东"),

    ASSET_MANAGEMENT("资管"),

    TRUST("信托"),

    PRIVATE_EQUITY("私募"),

    OTHER("其他");

    private String value;

    UnderWeightIdentification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
