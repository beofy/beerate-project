package cn.beerate.model.entity;

import cn.beerate.model.AuditStatus;
import cn.beerate.model.ItemType;
import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user_business",comment="用户名片表")
public class t_user_business extends Model {

    public static t_user_business getInstance(){
        t_user_business userBusiness = new t_user_business();
        userBusiness.setCreateTime(new Date());
        userBusiness.name="";
        userBusiness.company="";
        userBusiness.department="";
        userBusiness.title="";
        userBusiness.telCell="";
        userBusiness.telWork="";
        userBusiness.email="";
        userBusiness.businessCardUrl="";
        userBusiness.investPrefer="";
        userBusiness.aboutText="";
        userBusiness.workText="";
        userBusiness.auditStatus="";

        return userBusiness;
    }

    @Column(columnDefinition = "varchar(20) not null default '' comment '姓名'")
    private String name;

    @Column(columnDefinition = "varchar(20) not null default '' comment '公司'")
    private String company;

    @Column(columnDefinition = "varchar(20) not null default '' comment '部门'")
    private String department;

    @Column(columnDefinition = "varchar(20) not null default '' comment '职位'")
    private String title;

    @Column(columnDefinition = "varchar(20) not null default '' comment '手机号码'")
    @Pattern(regexp="^(((13[0-9]{1})|(15[0-35-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\\d{8})$",message="请输入正确的手机号码")
    private String telCell;

    @Column(columnDefinition = "varchar(20) not null default '' comment '公司号码'")
    private String telWork;

    @Column(columnDefinition = "varchar(50) not null default '' comment '公司地址'")
    private String address;

    @Column(columnDefinition = "varchar(50) not null default '' comment '联系邮箱'")
    @Email(message = "请输入正确的邮箱")
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
    private String auditStatus;

    @Column(columnDefinition = "datetime default null comment '审核时间'")
    private Date verifyTime;

    public void setInvestPrefer(ItemType itemType){
        this.investPrefer=itemType.name();
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus.name();
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private t_user user;

}
