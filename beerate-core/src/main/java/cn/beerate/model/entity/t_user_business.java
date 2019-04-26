package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user_business",comment="用户名片表")
public class t_user_business extends Model {

    @Column(columnDefinition = "varchar(20) not null default '' comment '姓名'")
    private String name;

    @Column(columnDefinition = "varchar(20) not null default '' comment '公司'")
    private String company;

    @Column(columnDefinition = "varchar(20) not null default '' comment '部门'")
    private String department;

    @Column(columnDefinition = "varchar(20) not null default '' comment '职位'")
    private String title;

    @Column(columnDefinition = "varchar(20) not null default '' comment '手机号码'")
    private String telCell;

    @Column(columnDefinition = "varchar(20) not null default '' comment '公司号码'")
    private String telWork;

    @Column(columnDefinition = "varchar(50) not null default '' comment '公司地址'")
    private String address;

    @Column(columnDefinition = "varchar(50) not null default '' comment '联系邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(50) not null default '' comment '名片保存地址'")
    private String businessCardUrl;

    @Column(columnDefinition = "varchar(10) not null default 0 comment '投资偏好'")
    private String investPrefer;

    @Column(columnDefinition = "varchar(255) not null default '' comment '个人介绍'")
    private String aboutText;

    @Column(columnDefinition = "varchar(255) not null default '' comment '工作经历'")
    private String workText;

    @Column(columnDefinition = "varchar(10) not null default '' comment '审核状态'")
    private String status;

    @Column(columnDefinition = "datetime default null comment '审核时间'")
    private Date verifyTime;

    public void setInvestph(INVESTPEFER investpefer) {
        this.investPrefer = investpefer.name();
    }

    public void setStatus(STATUS status) {
        this.status = status.name();
    }

    /**
     * 投资偏好
     */
    enum INVESTPEFER {

        /**
         * 老股转让
         */
        STOCK_TRANSFER,

        /**
         * 老股转让
         */
        PRE_IPO,

        /**
         * 老股转让
         */
        BLOCK_TRADE,

        /**
         * 老股转让
         */
        STOCK_PLEDGE

    }

    /**
     * 审核状态
     */
    enum STATUS{

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private t_user user;

}
