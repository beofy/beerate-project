package cn.beerate.model.dto;

import lombok.Data;

@Data
public class ItemDeliveryList {

    public ItemDeliveryList(long itemId, String itemName, String itemType, Boolean isDelivery) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.isDelivery = isDelivery;
    }

    private long itemId;

    private String itemName;

    private String itemType;

    private Boolean isDelivery;
}
