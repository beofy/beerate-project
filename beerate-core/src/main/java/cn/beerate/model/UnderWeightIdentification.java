package cn.beerate.model;

/**
 * 减持方身份
 */
public enum UnderWeightIdentification {
    /**
     * 无
     */
    NONE,

    /**
     * 大股东/实控人
     */
    STRONGER_STOCK_HOLDER,

    /**
     * 其他股东
     */
    OTHER_STOCK_HOLDER,

    /**
     * 资管
     */
    ASSET_MANAGEMENT,

    /**
     * 信托
     */
    TRUST,

    /**
     * 私募
     */
    PRIVATE_EQUITY,

    /**
     * 其他
     */
    OTHER

}
