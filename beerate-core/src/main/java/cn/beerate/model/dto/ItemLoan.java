package cn.beerate.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ItemLoan {

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
