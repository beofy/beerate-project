package cn.beerate.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StockPledge {
    public StockPledge(Long id, String financingBody, String stockCode, String stockNature, Date salesDeadline, String stockBlock, Boolean isQuoteOrActualController, Integer holdStockShares, Integer pledgeStockShares, Integer surplusPledgeStockShares, Double loanAmount, Double pledgeRates, String loanPeriod, Double financingPartyPaysCostRates, String recentOneYearProfitsDescription, String purpose, String repaymentDescription, String enhancementConfidenceMeasures, Date endTime, Boolean isUrgent, Boolean isPlatformAuthentication, Boolean isFirstHandle, String auditStatus) {
        this.id = id;
        this.financingBody = financingBody;
        this.stockCode = stockCode;
        this.stockNature = stockNature;
        this.salesDeadline = salesDeadline;
        this.stockBlock = stockBlock;
        this.isQuoteOrActualController = isQuoteOrActualController;
        this.holdStockShares = holdStockShares;
        this.pledgeStockShares = pledgeStockShares;
        this.surplusPledgeStockShares = surplusPledgeStockShares;
        this.loanAmount = loanAmount;
        this.pledgeRates = pledgeRates;
        this.loanPeriod = loanPeriod;
        this.financingPartyPaysCostRates = financingPartyPaysCostRates;
        this.recentOneYearProfitsDescription = recentOneYearProfitsDescription;
        this.purpose = purpose;
        this.repaymentDescription = repaymentDescription;
        this.enhancementConfidenceMeasures = enhancementConfidenceMeasures;
        this.endTime = endTime;
        this.isUrgent = isUrgent;
        this.isPlatformAuthentication = isPlatformAuthentication;
        this.isFirstHandle = isFirstHandle;
        this.auditStatus = auditStatus;
    }

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
