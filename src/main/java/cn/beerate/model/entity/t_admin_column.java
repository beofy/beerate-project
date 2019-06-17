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
@org.hibernate.annotations.Table(appliesTo = "t_admin_column",comment="后台栏目表")
public class t_admin_column extends Model {

    @Column(columnDefinition = "varchar(255) not null default '' comment '栏目名'")
    private String columnName;

    @Column(columnDefinition = "varchar(255) not null default '' comment '父类栏目id'")
    private String columnParentId;

}
