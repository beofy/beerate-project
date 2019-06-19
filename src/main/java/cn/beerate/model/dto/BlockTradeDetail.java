package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class BlockTradeDetail {

    @Id
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
        return confidenceIsPublic?expectedReturn:"********";
    }

    private Date confidencePeriod;

    public Object getConfidencePeriod(){
        return confidenceIsPublic?confidencePeriod:"********";
    }

    private String creditIdentification;

    public Object getCreditIdentification(){
        return confidenceIsPublic?creditIdentification:"";
    }

    private Integer confidenceShare;

    public Object getConfidenceShare(){
        return confidenceIsPublic?confidenceShare:"********";
    }

    private Boolean confidenceIsPublic;

    private Date endTime;

    private Boolean isUrgent;

    private Boolean isPlatformAuthentication;

    private Boolean isFirstHandle;

    private String auditStatus;

    private String description;

    private Long adminId;

    private Long userId;

}
