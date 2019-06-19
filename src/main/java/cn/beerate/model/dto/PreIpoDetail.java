package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class PreIpoDetail {

    @Id
    private Long id;

    private String preIpoName;

    private Boolean isNewThirdBoardListing;

    /*  ====================已挂牌==================== */
    private String stockCode;

    private Boolean isPrincipalUnderwriter;

    private Boolean isTutoringBrokerage;

    private String TutoringBrokerageName;
    /*  ====================已挂牌====================*/


    /*  ====================未挂牌==================== */
    private String bidName;

    public Object getBidName(){
        return companyNameIsPublic?this.companyName:"*******";
    }

    private Boolean companyNameIsPublic;

    private String companyName;

    private String city;
    /*  ====================未挂牌==================== */

    private String industryRealm;

    private Date iPOBaseDate;

    private String ratchetTerms;

    private String ratchetTermsDescription;

    /*  ====================已挂牌==================== */
    private Boolean isPriceNegotiable;

    private Double intentionalPrice;

    private Integer exchangeShares;

    private Double sharesAmount;

    private Double thresholdAmount;
    /* ====================已挂牌==================== */

    /* ====================未挂牌==================== */

    private String currency;

    private Double loanAmount;

    private Double IntentionValuation;

    private Double lastYearProfits;

    private String loanPeriod;

    private String purpose;

    private String investLightSpot;
    /*  ====================未挂牌==================== */

    private String businessProposalUri;

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
