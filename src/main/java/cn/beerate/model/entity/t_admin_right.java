package cn.beerate.model.entity;

import cn.beerate.model.Model;
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
@org.hibernate.annotations.Table(appliesTo = "t_admin_right",comment="后台权限表")
public class t_admin_right extends Model {

    @Column( columnDefinition = "bigint(20) not null  comment '管理员id'")
    private Long adminId;

    @Column( columnDefinition = "bigint(20) not null  comment '权限id'")
    private Long rightId;

}
