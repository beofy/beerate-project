package cn.beerate.model.dto;

import cn.beerate.model.ItemType;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Id;

public class UserItemAccept {

    @Id
    private Long id;

    private Long user_id;

    private Long item_id;

    private String name;

    private String  itemType;

    public ItemType getItemType() {
        return EnumUtils.getEnumIgnoreCase(ItemType.class,this.itemType);
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType.name();
    }
}
