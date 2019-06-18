package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BlockTrade {

    @Id
    private Long id;

    private String blockTradeName;

    private String stockCode;

    private Double exchangeRate;

    private Integer underweightShares;

    private Double underweightAmount;

    private Boolean isConfidence;

    private  String underweightIdentification;

}
