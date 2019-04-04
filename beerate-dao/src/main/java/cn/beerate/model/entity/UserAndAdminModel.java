package cn.beerate.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
class UserAndAdminModel extends Model{

    @Column(columnDefinition = "varchar(50) not null default '' comment '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(15) not null default '' comment '手机号'")
    private String mobile;

    @Column(columnDefinition = "varchar(15) not null default '' comment '邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(15) not null default '' comment '密码'")
    private String password;

}
