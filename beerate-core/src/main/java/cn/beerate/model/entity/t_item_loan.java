package cn.beerate.model.entity;


import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_loan",comment="项目融资表")
public class t_item_loan extends Model {

	/** 项目编号 */
	private String itemNo;

	/** 项目名称 */
	private String itemName;
	
	/** 公司名称 */
	private String companyName;
	
	/** 公司logo */
	private Long logoUri;
	
	/** 经营模式 */
	private Integer companyModel;
	
	/** 所属领域 */
	private Integer companyRealm;
	
	/** 项目类型 */
	private Integer companyType;

	/** 公司官网 */
	private String companyWebsite;
	
	/** IOS应用地址 */
	private String companyIosUrl;
	
	/** andriod应用地址 */
	private String companyAndroidUrl;
	
	/** 是否上市  */
	private boolean isQuoted;
	
	/** 股票代码  */
	private String stockCode;
	
	/**  融资金额 */
	private Double amount;
	
	private String amountUnit;

	/** 资金用途  */
	private String purpose;
	
	/**  借款期限 */
	private Integer period;
	
	/** 期限单位 */
	private String periodUnit;
	
	/**  还款来源 */
	private String repayment;
	
	/**  BP计划书 */
	private Long businessProposalUri;

	/* ~~补充资料~~ */
	/** 营业执照彩色扫描件（三证合一） */
	private Long businessLicenseUri;
	
	/**  前三年+本年度资产负债表、损益表、现金流量表 */
	private Long financialReportUri;
	
	/** 最近一年审计报告  */
	private Long auditReportUri;
	
	/**  有息负债明细：含起止时间、金额、担保或保证方 */
	private Long indebtednessUri;
	
	/** 近6个月银行流水（主要银行）  */
	private Long capitalFlowUri;

	/** 是否展示 */
	private Boolean isShow;
	
	private Integer status;

	//==========================================

	@ManyToOne
	@JoinColumn(name = "user_id")
	private t_user user;

	//==========================================

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private t_admin admin;

	//==========================================

	@OneToMany
	@JoinColumn(name = "item_loan_id")
	private List<t_item_message_board> message_boards;

}
