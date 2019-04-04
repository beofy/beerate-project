package cn.beerate.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 权限实体表
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_right",comment="权限表")
public class t_right extends Model {

    @Column(columnDefinition = "varchar(255) not null default comment '权限地址'")
    private String control;

    @Column(columnDefinition = "varchar(255) not null default comment '权限描述'")
    private String description;



}
