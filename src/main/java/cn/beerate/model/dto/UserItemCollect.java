package cn.beerate.model.dto;

import cn.beerate.model.ItemType;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserItemCollect {

    @Id
    private Long id;

    private String name;

    private String  itemType;

    public ItemType getItemType() {
        return EnumUtils.getEnumIgnoreCase(ItemType.class,this.itemType);
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType.name();
    }

}
