package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class StockTransferDetail {

    @Id
    private long id;

    private String bidName;

    private Boolean isQuoted;

    private Boolean companyNameIsPublic;

    private String companyName;

    public Object getCompanyName(){
        return this.companyNameIsPublic?this.companyName:"******";
    }

    private String industryRealm;

    private String currency;

    private Double lastYearProfits;

    private Double currentValuation;

    private Double transferAmount;

    private Boolean isPrivacyEquityRatio;

    private Double equityRatio;

    public Object getEquityRatio(){
        return this.isPrivacyEquityRatio?equityRatio:"******";
    }

    private String investLightSpot;

    private String contact;

    private String contactMobile;

    private String contentDescription;

    private Date endTime;

    private Boolean isUrgent;

    private Boolean isPlatformAuthentication;

    private Boolean isFirstHandle;

    private String auditStatus;

    private String description;

    private Long adminId;

    private Long userId;

}
