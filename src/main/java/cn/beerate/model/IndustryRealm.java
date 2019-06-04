package cn.beerate.model;

/**
 * 行业领域
 */
public enum IndustryRealm {

    NONE("请选择"),

    AI("人工智能"),

    TMT("TMT"),

    BIG_DATA("大数据"),

    BLOCK_CHAIN("区块链"),

    CLOUD_COMPUTING("云计算"),

    NWE_ENERGY("新能源"),

    INTERNET_OF_THINGS("物联网"),

    SPORTS_RECREATION("文体娱乐"),

    PEDICURE("足疗健康"),

    HIGH_MANUFACTURING("高端制造"),

    AR_VR("AR/VR"),

    FINANCIAL_PAYMENT("金融支付"),

    INFORMATION_SECURITY("信息安全"),

    BANK("银行"),

    TRANSPORTATION("交通出行"),

    INTELLECTUAL_PROPERTY("知识产权"),

    GREEN_INDUSTRY("绿色产业"),

    SHARING_ECONOMY("共享经济"),

    E_BUSINESS("电子商务"),

    ONLINE_FINANCE("互联网金融"),

    O2O("O2O"),

    ENVIRONMENTAL_PROTECTION("环保"),

    NEW_MATERIAL("新材料"),

    PENSION_INDUSTRY("养老产业"),

    NEW_RETAIL("新零售"),

    MOTHER_AND_INFANTS("母婴"),

    NEWS_MEDIA("新闻媒体"),

    INVESTMENT_BANK("投资银行");

    private String value;

    IndustryRealm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
