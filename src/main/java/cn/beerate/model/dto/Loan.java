package cn.beerate.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Loan {
    public Loan(Long id, String itemName, String companyName, String logoUri, String industryRealm, String companyWebsite, String companyIosUrl, String companyAndroidUrl, Boolean isQuoted, String stockCode, Double amount, String amountUnit, String purpose, Integer period, String periodUnit, String repayment, String businessProposalUri, String businessLicenseUri, String financialReportUri, String auditReportUri, String indebtednessUri, String capitalFlowUri, Date endTime, Boolean isUrgent, Boolean isPlatformAuthentication, Boolean isFirstHandle, String auditStatus) {
        this.id = id;
        this.itemName = itemName;
        this.companyName = companyName;
        this.logoUri = logoUri;
        this.industryRealm = industryRealm;
        this.companyWebsite = companyWebsite;
        this.companyIosUrl = companyIosUrl;
        this.companyAndroidUrl = companyAndroidUrl;
        this.isQuoted = isQuoted;
        this.stockCode = stockCode;
        this.amount = amount;
        this.amountUnit = amountUnit;
        this.purpose = purpose;
        this.period = period;
        this.periodUnit = periodUnit;
        this.repayment = repayment;
        this.businessProposalUri = businessProposalUri;
        this.businessLicenseUri = businessLicenseUri;
        this.financialReportUri = financialReportUri;
        this.auditReportUri = auditReportUri;
        this.indebtednessUri = indebtednessUri;
        this.capitalFlowUri = capitalFlowUri;
        this.endTime = endTime;
        this.isUrgent = isUrgent;
        this.isPlatformAuthentication = isPlatformAuthentication;
        this.isFirstHandle = isFirstHandle;
        this.auditStatus = auditStatus;
    }

    private Long id;

    private String itemName;

    private String companyName;

    private String logoUri;

    private String industryRealm;

    private String companyWebsite;

    private String companyIosUrl;

    private String companyAndroidUrl;

    private Boolean isQuoted;

    private String stockCode;

    private Double amount;

    private String amountUnit;

    private String purpose;

    private Integer period;

    private String periodUnit;

    private String repayment;

    private String businessProposalUri;

    /* ======================补充资料====================== */
    private String businessLicenseUri;

    private String financialReportUri;

    private String auditReportUri;

    private String indebtednessUri;

    private String capitalFlowUri;

    /* ======================补充资料====================== */

    private Date endTime;

    private Boolean isUrgent;

    private Boolean isPlatformAuthentication;

    private Boolean isFirstHandle;

    private String auditStatus;

}
