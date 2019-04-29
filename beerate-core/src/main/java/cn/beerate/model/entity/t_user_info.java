package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user_info",comment="前台用户信息表")
public class t_user_info extends Model {


    @Column(columnDefinition = "varchar(20) not null default '' comment '真实姓名'")
    @NotBlank(message = "真实姓名不能为空")
    private String realityName;

    @Column(columnDefinition = "varchar(20) not null default '' comment '身份证号码'")
    @NotBlank(message = "身份证号码不能为空")
    private String idNumber;

    @Column(columnDefinition = "int(1) not null default 0 comment '性别'")
    private Integer sex;

    @Column(columnDefinition = "int(3) not null default 0 comment '年龄'")
    private Integer age;

    @Column(columnDefinition = "varchar(255) not null default '' comment '正面身份证'")
    @NotBlank(message = "正面身份证不能为空")
    private String faceIdCard;

    @Column(columnDefinition = "varchar(255) not null default '' comment '反面身份证'")
    @NotBlank(message = "反面身份证不能为空")
    private String reverseIdCard;

    private Integer status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private t_user user;

}
