package cn.beerate.model.dto;

import lombok.Data;

@Data
public class ItemLoanList {

    public ItemLoanList(long id, String itemName, String industryRealm, Double amount, Integer period, String auditStatus) {
        this.id = id;
        this.itemName = itemName;
        this.industryRealm = industryRealm;
        this.amount = amount;
        this.period = period;
        this.auditStatus = auditStatus;
    }

    private long id;

    private String itemName;

    private String industryRealm;

    private Double amount;

    private Integer period;

    private String auditStatus;

}
