package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class BlockTrade {

    public BlockTrade(String blockTradeName, String stockCode) {
        this.blockTradeName = blockTradeName;
        this.stockCode = stockCode;
    }

    private String blockTradeName;

    private String stockCode;

}
