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
        if (!confidenceIsPublic){
            return "********";
        }
        return expectedReturn;
    }

    private Date confidencePeriod;

    public Object getConfidencePeriod(){
        if (!confidenceIsPublic){
            return "********";
        }
        return confidencePeriod;
    }

    private String creditIdentification;

    public Object getCreditIdentification(){
        if (!confidenceIsPublic){
            return "********";
        }
        return creditIdentification;
    }

    private Integer confidenceShare;

    public Object getConfidenceShare(){
        if (!confidenceIsPublic){
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

    private String description;

    private Long adminId;

    private Long userId;

}
