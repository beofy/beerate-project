package cn.beerate.model.entity;

import cn.beerate.model.ItemType;
import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_user_item_delivery", comment = "用户项目投递表")
public class t_user_item_delivery extends Model {

    @Column( columnDefinition = "bigint(20) not null  comment '用户id'")
    private Long userId;

    @Column( columnDefinition = "bigint(20) not null  comment '项目id'")
    private Long itemId;

    @Column( columnDefinition = "varchar(50) not null default '' comment '项目名称'")
    private String name;

    @Column( columnDefinition = "varchar(30) not null default '' comment '项目类型'")
    private String  itemType;

    public ItemType getItemType() {
        return EnumUtils.getEnumIgnoreCase(ItemType.class,this.itemType);
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType.name();
    }
}
