package cn.beerate.model.dto;

import lombok.Data;

@Data
public class StockTransferList {

    public StockTransferList(long id, String bidName, Boolean isQuoted, String industryRealm, Double transferAmount, String auditStatus) {
        this.id = id;
        this.bidName = bidName;
        this.isQuoted = isQuoted;
        this.industryRealm = industryRealm;
        this.transferAmount = transferAmount;
        this.auditStatus = auditStatus;
    }

    private long id;

    private String bidName;

    private Boolean isQuoted;

    private String industryRealm;

    private Double transferAmount;

    private String auditStatus;

}
