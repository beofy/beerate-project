package cn.beerate.model.entity;


import cn.beerate.model.AmountUnit;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.ItemModel;
import cn.beerate.model.PeriodUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_loan",comment="项目融资表")
public class t_item_loan extends ItemModel {

	@Column(columnDefinition = "varchar(128) not null default '' comment '项目名称'")
	private String itemName;
	
	@Column(columnDefinition = "varchar(64) not null default '' comment '公司名称'")
	private String companyName;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment '公司logo'")
	private String logoUri;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment '行业领域'")
	private String industryRealm;

	public void setIndustryRealm(IndustryRealm industryRealm) {
		this.industryRealm = industryRealm.name();
	}

	public IndustryRealm getIndustryRealm(){
		return EnumUtils.getEnumIgnoreCase(IndustryRealm.class,this.industryRealm);
	}

	@Column(columnDefinition = "varchar(255) not null default '' comment '公司官网'")
	private String companyWebsite;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment 'IOS应用地址'")
	private String companyIosUrl;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment 'Android应用地址'")
	private String companyAndroidUrl;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment '是否上市'")
	private Boolean isQuoted;
	
	@Column(columnDefinition = "varchar(6) not null default '' comment '股票代码'")
	private String stockCode;
	
	@Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '融资金额'")
	private Double amount;

	@Column(columnDefinition = "varchar(5) not null default '' comment '金额单位'")
	private String amountUnit;

	public void setAmountUnit(AmountUnit amountUnit){
		this.amountUnit=amountUnit.name();
	}

    public AmountUnit getAmountUnit() {
        return EnumUtils.getEnumIgnoreCase(AmountUnit.class,amountUnit);
    }

    @Column(columnDefinition = "varchar(255) not null default '' comment '资金用途'")
	private String purpose;
	
	@Column(columnDefinition = "int(3) not null default 0 comment '借款期限'")
	private Integer period;
	
	@Column(columnDefinition = "varchar(5) not null default '' comment '期限单位'")
	private String periodUnit;

	public void setPeriodUnit(PeriodUnit periodUnit){
		this.periodUnit=periodUnit.name();
	}

	public PeriodUnit getPeriodUnit(){
		return EnumUtils.getEnumIgnoreCase(PeriodUnit.class,this.periodUnit);
	}

	@Column(columnDefinition = "varchar(255) not null default '' comment '还款来源'")
	private String repayment;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment 'BP计划书'")
	private String businessProposalUri;

	@Column(columnDefinition = "varchar(255) not null default '' comment '营业执照彩色扫描件（三证合一）'")
	private String businessLicenseUri;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment '前三年+本年度资产负债表、损益表、现金流量表'")
	private String financialReportUri;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment '最近一年审计报告'")
	private String auditReportUri;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment '有息负债明细：含起止时间、金额、担保或保证方'")
	private String indebtednessUri;
	
	@Column(columnDefinition = "varchar(255) not null default '' comment '近6个月银行流水（主要银行）'")
	private String capitalFlowUri;

}
