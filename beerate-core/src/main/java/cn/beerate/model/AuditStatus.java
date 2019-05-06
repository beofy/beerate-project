package cn.beerate.model;

/**
 * 审核状态枚举类
 */
public enum AuditStatus {

    NONE("无"),

    NO_AUDIT("审核失败"),

    WAIT_AUDIT("等待审核"),

    SUPPLEMENT("补充资料"),

    PASS_AUDIT("通过审核"),

    FAIL_AUDIT("未通过审核");

    private String value;

    AuditStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
