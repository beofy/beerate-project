package cn.beerate.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public class UserAndAdminModel extends Model{

    @Column(columnDefinition = "varchar(50) not null default '' comment '用户名'")
    @NotBlank(message = "用户名不能空")
    private String username;

    @Column(columnDefinition = "varchar(20) not null default '' comment '手机号'")
    @Pattern(regexp="^(((13[0-9]{1})|(15[0-35-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\\d{8})$",message="请输入正确的手机号码")
    private String mobile;

    @Column(columnDefinition = "varchar(64) not null default '' comment '邮箱'")
    @Email(message = "请输入正确的邮箱")
    private String email;

    @Column(columnDefinition = "varchar(128) not null default '' comment '密码'")
    @NotBlank(message = "密码不能为空")
    private String password;

}
