package cn.beerate.model;

/**
 * 增信身份
 */
public enum CreditIdentification {

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
     *  第三方担保
     */
    GUARANTEE,

    /**
     * 其他
     */
    OTHER

}
