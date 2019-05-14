package cn.beerate.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StockTransfer {

    public StockTransfer(long id, String bidName, Boolean isQuoted, Boolean companyNameIsPublic, String companyName, String industryRealm, String currency, Double lastYearProfits, Double currentValuation, Double transferAmount, Boolean isPrivacyEquityRatio, Double equityRatio, String investLightSpot, String contact, String contactMobile, String contentDescription, Date endTime, Boolean isUrgent, Boolean isPlatformAuthentication, Boolean isFirstHandle, String auditStatus) {
        this.id = id;
        this.bidName = bidName;
        this.isQuoted = isQuoted;
        this.companyNameIsPublic = companyNameIsPublic;
        this.companyName = companyName;
        this.industryRealm = industryRealm;
        this.currency = currency;
        this.lastYearProfits = lastYearProfits;
        this.currentValuation = currentValuation;
        this.transferAmount = transferAmount;
        this.isPrivacyEquityRatio = isPrivacyEquityRatio;
        this.equityRatio = equityRatio;
        this.investLightSpot = investLightSpot;
        this.contact = contact;
        this.contactMobile = contactMobile;
        this.contentDescription = contentDescription;
        this.endTime = endTime;
        this.isUrgent = isUrgent;
        this.isPlatformAuthentication = isPlatformAuthentication;
        this.isFirstHandle = isFirstHandle;
        this.auditStatus = auditStatus;
    }

    private long id;

    private String bidName;

    private Boolean isQuoted;

    private Boolean companyNameIsPublic;

    private String companyName;

    private String industryRealm;

    private String currency;

    private Double lastYearProfits;

    private Double currentValuation;

    private Double transferAmount;

    private Boolean isPrivacyEquityRatio;

    private Double equityRatio;

    private String investLightSpot;

    private String contact;

    private String contactMobile;

    private String contentDescription;

    private Date endTime;

    private Boolean isUrgent;

    private Boolean isPlatformAuthentication;

    private Boolean isFirstHandle;

    private String auditStatus;

}
