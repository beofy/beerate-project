package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class StockPledgeDetail {

    @Id
    private Long id;

    private String financingBody;

    private String stockCode;

    private String stockNature;

    private Date salesDeadline;

    private String stockBlock;

    private Boolean isQuoteOrActualController;

    private Integer holdStockShares;

    private Integer pledgeStockShares;

    private Integer surplusPledgeStockShares;

    private Double loanAmount;

    private Double pledgeRates;

    private String loanPeriod;

    private Double financingPartyPaysCostRates;

    private String recentOneYearProfitsDescription;

    private String purpose;

    private String repaymentDescription;

    private String enhancementConfidenceMeasures;

    private Date endTime;

    private Boolean isUrgent;

    private Boolean isPlatformAuthentication;

    private Boolean isFirstHandle;

    private String auditStatus;

}
