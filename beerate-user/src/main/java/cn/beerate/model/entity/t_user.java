package cn.beerate.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;


@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user",comment="前台用户表")
public class t_user extends UserAndAdminModel{

    /** 连续登录失败次数 */
    private int password_continue_fails;

    /** 密码连续错误被锁定 */
    private boolean is_password_locked;

    /** 密码连续错误被锁定时间 */
    private Date password_locked_time;

    /** 是否允许登录(锁定) */
    private boolean is_allow_login;

    /** 后台管理员执行锁定的时间 */
    private Date lock_time;

    /** 登录次数 */
    private int login_count;

    /** 上次登录时间 */
    private Date last_login_time;

    /** 上次登录ip */
    private String last_login_ip;

    /** 上次登录入口(枚举)：1 pc 2 app 3 wechat */
    private int last_login_client;


}
