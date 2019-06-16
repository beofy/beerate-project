package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class LoanDetail {

    @Id
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

    private String businessLicenseUri;

    private String financialReportUri;

    private String auditReportUri;

    private String indebtednessUri;

    private String capitalFlowUri;

    private Date endTime;

    private Boolean isUrgent;

    private Boolean isPlatformAuthentication;

    private Boolean isFirstHandle;

    private String auditStatus;

    private String description;

    private Long admin_id;

    private Long user_id;

}
