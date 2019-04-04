package cn.beerate.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_colum",comment="栏目表")
public class t_colum extends Model{

    @Column(columnDefinition = "varchar(255) not null default '' comment '栏目名'")
    private String columName;

    @Column(columnDefinition = "varchar(255) not null default '' comment '父类栏目id'")
    private String columParentId;

}
