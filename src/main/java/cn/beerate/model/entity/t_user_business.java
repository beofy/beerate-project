package cn.beerate.model.entity;

import cn.beerate.model.AuditStatus;
import cn.beerate.model.ItemType;
import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user_business",comment="用户名片表")
public class t_user_business extends Model {

    @Column(columnDefinition = "varchar(20) default '' comment '姓名'")
    private String name;

    @Column(columnDefinition = "varchar(20) default '' comment '公司'")
    private String company;

    @Column(columnDefinition = "varchar(20) default '' comment '部门'")
    private String department;

    @Column(columnDefinition = "varchar(20) default '' comment '职位'")
    private String title;

    @Column(columnDefinition = "varchar(20) default '' comment '手机号码'")
    private String telCell;

    @Column(columnDefinition = "varchar(20) default '' comment '公司号码'")
    private String telWork;

    @Column(columnDefinition = "varchar(50) default '' comment '公司地址'")
    private String address;

    @Column(columnDefinition = "varchar(50) default '' comment '联系邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(255) default '' comment '名片保存地址'")
    private String businessCardUri;

    @Column(columnDefinition = "varchar(30) default 0 comment '投资偏好'")
    private String investPrefer;

    public void setInvestPrefer(ItemType investPrefer){
        this.investPrefer=investPrefer.name();
    }

    public ItemType getInvestPrefer(){
        return EnumUtils.getEnumIgnoreCase(ItemType.class,this.investPrefer);
    }

    @Column(columnDefinition = "varchar(255) default '' comment '个人介绍'")
    private String aboutText;

    @Column(columnDefinition = "varchar(255) default '' comment '工作经历'")
    private String workText;

    @Column(columnDefinition = "varchar(10) default '' comment '审核状态'")
    private String auditStatus;

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus.name();
    }

    public AuditStatus getAuditStatus(){
        return EnumUtils.getEnumIgnoreCase(AuditStatus.class,this.auditStatus);
    }

    @Column(columnDefinition = "datetime default null comment '审核时间'")
    private Date verifyTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private t_user user;

}
