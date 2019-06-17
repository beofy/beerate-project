package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_message_board",comment="项目留言表")
public class t_item_message_board extends Model {
    @Column( columnDefinition = "bigint(20) not null  comment '用户id'")
    private Long userId;

    @Column( columnDefinition = "bigint(20) not null  comment '项目id'")
    private Long itemId;

    @Column( columnDefinition = "varchar(500) not null default '' comment '描述'")
    private String description;

    @Column( columnDefinition = "varchar(30) not null default '' comment '项目类型'")
    private String  itemType;
}
