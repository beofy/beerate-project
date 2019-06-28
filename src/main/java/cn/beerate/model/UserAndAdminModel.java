package cn.beerate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public class UserAndAdminModel extends Model{

    @Column(columnDefinition = "varchar(50) not null default '' comment '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(20) not null default '' comment '手机号'")
    private String mobile;

    @Column(columnDefinition = "varchar(64) not null default '' comment '邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(128) not null default '' comment '密码'")
    private String password;

    @Column(columnDefinition = "varchar(128) not null default '' comment '会话id'")
    private String sessionId;
}
