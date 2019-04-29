package cn.beerate.model;

/**
 * 审核状态枚举类
 */
public enum  AuditStatus {
        /**
         * 审核失败
         */
        NO_AUDIT,

        /**
         * 等待审核
         */
        WAIT_AUDIT,

        /**
         * 通过审核
         */
        PASS_AUDIT,

        /**
         * 未通过审核
         */
        FAIL_AUDIT

}
