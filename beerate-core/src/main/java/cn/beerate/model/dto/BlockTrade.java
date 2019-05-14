package cn.beerate.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BlockTrade {

    public BlockTrade(Long id, String blockTradeName, String stockCode, Double exchangeRate, Integer underweightShares, Double underweightAmount, String underweightIdentification, Boolean isConfidence, Double expectedReturn, Date confidencePeriod, String creditIdentification, Integer confidenceShare, Boolean confidenceIsPublic, Date endTime, Boolean isUrgent, Boolean isPlatformAuthentication, Boolean isFirstHandle, String auditStatus) {
        this.id = id;
        this.blockTradeName = blockTradeName;
        this.stockCode = stockCode;
        this.exchangeRate = exchangeRate;
        this.underweightShares = underweightShares;
        this.underweightAmount = underweightAmount;
        this.underweightIdentification = underweightIdentification;
        this.isConfidence = isConfidence;
        this.expectedReturn = expectedReturn;
        this.confidencePeriod = confidencePeriod;
        this.creditIdentification = creditIdentification;
        this.confidenceShare = confidenceShare;
        this.confidenceIsPublic = confidenceIsPublic;
        this.endTime = endTime;
        this.isUrgent = isUrgent;
        this.isPlatformAuthentication = isPlatformAuthentication;
        this.isFirstHandle = isFirstHandle;
        this.auditStatus = auditStatus;
    }

    private Long id;

    private String blockTradeName;

    private String stockCode;

    private Double exchangeRate;

    private Integer underweightShares;

    private Double underweightAmount;

    private String underweightIdentification;

    private Boolean isConfidence;

    private Double expectedReturn;

    public Object getExpectedReturn(){
        if (confidenceIsPublic){
            return "********";
        }
        return expectedReturn;
    }

    private Date confidencePeriod;

    public Object getConfidencePeriod(){
        if (confidenceIsPublic){
            return "********";
        }
        return confidencePeriod;
    }

    private String creditIdentification;

    public Object getCreditIdentification(){
        if (confidenceIsPublic){
            return "********";
        }
        return creditIdentification;
    }

    private Integer confidenceShare;

    public Object getConfidenceShare(){
        if (confidenceIsPublic){
            return "********";
        }
        return confidenceShare;
    }

    private Boolean confidenceIsPublic;

    private Date endTime;

    private Boolean isUrgent;

    private Boolean isPlatformAuthentication;

    private Boolean isFirstHandle;

    private String auditStatus;

}
