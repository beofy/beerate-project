package cn.beerate.model.dto;

import lombok.Data;

@Data
public class StockPledgeList {

    public StockPledgeList(long id, String financingBody, String stockCode, String stockNature, String stockBlock, Integer holdStockShares, String auditStatus) {
        this.id = id;
        this.financingBody = financingBody;
        this.stockCode = stockCode;
        this.stockNature = stockNature;
        this.stockBlock = stockBlock;
        this.holdStockShares = holdStockShares;
        this.auditStatus = auditStatus;
    }

    private long id;

    private String financingBody;

    private String stockCode;

    private String stockNature;

    private String stockBlock;

    private Integer holdStockShares;

    private String auditStatus;
}
