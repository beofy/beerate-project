package cn.beerate.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;


@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user",comment="前台用户表")
public class t_user extends UserAndAdminModel{

    @Column(columnDefinition = "int not null default 0 comment '密码连续错误次数'")
    private Integer password_continue_fails;

    @Column(columnDefinition = "datetime comment '密码锁定时间'")
    private Date password_locked_time;

    @Column(columnDefinition = "bit not null default 0 comment '是否允许登录'")
    private Boolean is_allow_login;

    @Column(columnDefinition = "datetime comment '锁定时间'")
    private Date lock_time;

    @Column(columnDefinition = "int comment '登录次数'")
    private Integer login_count;

    @Column(columnDefinition = "datetime comment '最后一次登录时间'")
    private Date last_login_time;

    @Column(columnDefinition = "varchar(20) default '' comment '最后一次登录ip'")
    private String last_login_ip;

    @Column(columnDefinition = "varchar(20) default '' comment '最后一次登录渠道'")
    private String last_login_client;

    @Column(columnDefinition = "varchar(20) default '' comment '注册ip'")
    private String registe_ip;

    @Column(columnDefinition = "varchar(20) default '' comment '注册渠道'")
    private String registe_channel;

}
