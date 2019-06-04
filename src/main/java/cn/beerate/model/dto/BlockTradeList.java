package cn.beerate.model.dto;

import lombok.Data;

@Data
public class BlockTradeList {

    public BlockTradeList(long id, String blockTradeName, String stockCode, Double exchangeRate, Integer underweightShares, Double underweightAmount, String auditStatus) {
        this.id = id;
        this.blockTradeName = blockTradeName;
        this.stockCode = stockCode;
        this.exchangeRate = exchangeRate;
        this.underweightShares = underweightShares;
        this.underweightAmount = underweightAmount;
        this.auditStatus = auditStatus;
    }

    private long id;

    private String blockTradeName;

    private String stockCode;

    private Double exchangeRate;

    private Integer underweightShares;

    private Double underweightAmount;

    private String auditStatus;

}
