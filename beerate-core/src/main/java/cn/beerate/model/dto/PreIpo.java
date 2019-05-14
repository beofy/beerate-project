package cn.beerate.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PreIpo {
    public PreIpo(Long id, String preIpoName, Boolean isNewThirdBoardListing, String stockCode, Boolean isPrincipalUnderwriter, Boolean isTutoringBrokerage, String tutoringBrokerageName, String bidName, Boolean companyNameIsPublic, String companyName, String city, String industryRealm, Date iPOBaseDate, String ratchetTerms, String ratchetTermsDescription, Boolean isPriceNegotiable, Double intentionalPrice, Integer exchangeShares, Double sharesAmount, Double thresholdAmount, String currency, Double loanAmount, Double intentionValuation, Double lastYearProfits, String loanPeriod, String purpose, String investLightSpot, String businessProposalUri, String contact, String contactMobile, String contentDescription, Date endTime, Boolean isUrgent, Boolean isPlatformAuthentication, Boolean isFirstHandle, String auditStatus) {
        this.id = id;
        this.preIpoName = preIpoName;
        this.isNewThirdBoardListing = isNewThirdBoardListing;
        this.stockCode = stockCode;
        this.isPrincipalUnderwriter = isPrincipalUnderwriter;
        this.isTutoringBrokerage = isTutoringBrokerage;
        TutoringBrokerageName = tutoringBrokerageName;
        this.bidName = bidName;
        this.companyNameIsPublic = companyNameIsPublic;
        this.companyName = companyName;
        this.city = city;
        this.industryRealm = industryRealm;
        this.iPOBaseDate = iPOBaseDate;
        this.ratchetTerms = ratchetTerms;
        this.ratchetTermsDescription = ratchetTermsDescription;
        this.isPriceNegotiable = isPriceNegotiable;
        this.intentionalPrice = intentionalPrice;
        this.exchangeShares = exchangeShares;
        this.sharesAmount = sharesAmount;
        this.thresholdAmount = thresholdAmount;
        this.currency = currency;
        this.loanAmount = loanAmount;
        IntentionValuation = intentionValuation;
        this.lastYearProfits = lastYearProfits;
        this.loanPeriod = loanPeriod;
        this.purpose = purpose;
        this.investLightSpot = investLightSpot;
        this.businessProposalUri = businessProposalUri;
        this.contact = contact;
        this.contactMobile = contactMobile;
        this.contentDescription = contentDescription;
        this.endTime = endTime;
        this.isUrgent = isUrgent;
        this.isPlatformAuthentication = isPlatformAuthentication;
        this.isFirstHandle = isFirstHandle;
        this.auditStatus = auditStatus;
    }

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

}
