package cn.beerate.model.entity;

import cn.beerate.model.UserAndAdminModel;
import cn.beerate.request.ChannelType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user",comment="前台用户表")
public class t_user extends UserAndAdminModel {

    public static t_user getInstance(){
        t_user user =new t_user();
        user.setCreateTime(new Date());
        user.setCreateTime(new Date());
        user.setUsername("");
        user.setMobile("");
        user.setEmail("");
        user.photo="";
        user.password_continue_fails=0;
        user.is_allow_login=true;
        user.login_count=0;
        user.last_login_ip="";
        user.last_login_client="";
        user.reg_ip="";
        user.reg_channel="";

        return user;
    }

    @Column(columnDefinition = "varchar(255) not null default '' comment '头像'")
    private String photo;

    @Column(columnDefinition = "int not null default 0 comment '密码连续错误次数'")
    private Integer password_continue_fails;

    @Column(columnDefinition = "datetime comment '密码锁定时间'")
    private Date password_locked_time;

    @Column(columnDefinition = "bit not null default 0 comment '是否允许登录'")
    private Boolean is_allow_login;

    @Column(columnDefinition = "datetime comment '锁定时间'")
    private Date lock_time;

    @Column(columnDefinition = "int  not null default 0 comment '登录次数'")
    private Integer login_count;

    @Column(columnDefinition = "datetime comment '最后一次登录时间'")
    private Date last_login_time;

    @Column(columnDefinition = "varchar(20) default '' comment '最后一次登录ip'")
    private String last_login_ip;

    @Column(columnDefinition = "varchar(20) default '' comment '最后一次登录渠道'")
    private String last_login_client;

    @Column(columnDefinition = "varchar(20) default '' comment '注册ip'")
    private String reg_ip;

    @Column(columnDefinition = "varchar(20) default '' comment '注册渠道'")
    private String reg_channel;

    public void setReg_channel(ChannelType channel) {
        this.reg_channel = channel.name();
    }

    //======================基本信息======================
    @OneToOne(mappedBy = "user")
    private t_user_info user_info;

    @OneToOne(mappedBy = "user")
    private t_user_business user_business;


    //======================项目关联======================
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_item_loan> item_loan;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_item_block_trade> block_trade;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_item_pre_ipo> pre_ipo;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_item_stock_pledge> stock_pledge;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_item_stock_transfer> stock_transfer;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_item_overseas_listing> overseas_listing;


    //======================项目收藏======================
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_item_collect> item_collects;


    //======================关注，联系，访客联系======================
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_user_attention> my_attention;

    @OneToMany
    @JoinColumn(name = "attention_user_id")
    private List<t_user_attention> attention_me;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_user_contact> my_contact;

    @OneToMany
    @JoinColumn(name = "contact_user_id")
    private List<t_user_contact> contact_me;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<t_user_visitor> my_visitor;

    @OneToMany
    @JoinColumn(name = "visitor_user_id")
    private List<t_user_visitor> visitor_me;

}
