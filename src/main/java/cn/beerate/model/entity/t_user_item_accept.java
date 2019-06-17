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
@org.hibernate.annotations.Table(appliesTo = "t_user_item_accept", comment = "用户项目接收表")
public class t_user_item_accept extends Model {

    @Column( columnDefinition = "bigint(20) not null  comment '用户id'")
    private Long user_id;

    @Column( columnDefinition = "bigint(20) not null  comment '项目投递表id'")
    private Long user_item_delivery_id;

}
