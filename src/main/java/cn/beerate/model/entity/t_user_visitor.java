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
@org.hibernate.annotations.Table(appliesTo = "t_user_visitor",comment="用户访客表")
public class t_user_visitor extends Model {

    @Column( columnDefinition = "bigint(20) not null  comment '用户id'")
    private Long userId;

    @Column( columnDefinition = "bigint(20) not null  comment '被访问的用户id'")
    private Long attentionUserId;

}
