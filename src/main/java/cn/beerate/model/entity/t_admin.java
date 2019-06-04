package cn.beerate.model.entity;

import cn.beerate.model.UserAndAdminModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


/**
 * 后台管理员表
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_admin",comment="后台管理员表")
public class t_admin extends UserAndAdminModel {

    @Column(columnDefinition = "varchar(255) not null default '' comment '头像'")
    private String photo;

    @Column(columnDefinition = "varchar(20) not null default '' comment '真实姓名'")
    private String realityName;

    @Column(columnDefinition = "varchar(20) not null default '' comment '部门名称'")
    private String department;

    @Column(columnDefinition = "varchar(20) not null default '' comment '职位'")
    private String position;

    @Column(columnDefinition = "bit not null default 0 comment '锁定状态'")
    private Boolean lockStatus;

    @Column(columnDefinition = "bigint not null default 0 comment '登录次数'")
    private Long loginCount;

    @Column(columnDefinition = "datetime comment '最后一次登录时间'")
    private Date lastLoginTime;

    @Column(columnDefinition = "varchar(25) not null default '' comment '最后一次登录IP'")
    private String lastLoginIp;

    @Column(columnDefinition = "varchar(255) not null default '' comment '简介'")
    private String remark;

    @Column(columnDefinition = "bigint not null default 0 comment '创建者ID'")
    private Long creatorId;

    //======================项目关联======================
    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<t_item_loan> item_loan;

    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<t_item_block_trade> block_trade;

    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<t_item_pre_ipo> pre_ipo;

    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<t_item_stock_pledge> stock_pledge;

    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<t_item_stock_transfer> stock_transfer;

    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<t_item_overseas_listing> overseas_listing;

}
