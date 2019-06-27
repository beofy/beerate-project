package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_platform_setting",comment="平台设置参数表")
public class t_platform_setting extends Model {

    @Column(columnDefinition = "varchar(128) not null default '' comment '参数键'")
    private String setKey;

    @Column(columnDefinition = "varchar(128) not null default '' comment '参数值'")
    private String setValue;

    @Column(columnDefinition = "varchar(128) not null default '' comment '描述'")
    private String description;

}
