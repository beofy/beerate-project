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
	private String item_no;

	/** 项目名称 */
	private String item_name;
	
	/** 公司名称 */
	private String company_name;
	
	/** 公司logo */
	private Long logo_img_id;
	
	/** 经营模式 */
	private Integer company_model;
	
	/** 所属领域 */
	private Integer company_realm;
	
	/** 项目类型 */
	private Integer company_type;

	/** 公司官网 */
	private String company_website;
	
	/** IOS应用地址 */
	private String company_ios_url;
	
	/** andriod应用地址 */
	private String company_andriod_url;
	
	/** 是否上市  */
	private boolean is_quoted;
	
	/** 股票代码  */
	private String stockcode;
	
	/**  融资金额 */
	private Integer amount;
	
	private Integer amount_unit;
	
	/** 资金用途  */
	private String purpose;
	
	/**  借款期限 */
	private Integer period;
	
	/** 期限单位 */
	private Integer period_unit;
	
	/**  还款来源 */
	private String repayment;
	
	/**  BP计划上传 */
	private Long bp_book_id;
	
	/**  BP计划上传 */
	private String watermark_file_url;
	
	/* ~~补充资料~~ */
	/** 营业执照彩色扫描件（三证合一） */
	private Long business_license_id;
	
	/**  前三年+本年度资产负债表、损益表、现金流量表 */
	private Long financial_report_id;
	
	/** 最近一年审计报告  */
	private Long audit_report_id;
	
	/**  有息负债明细：含起止时间、金额、担保或保证方 */
	private Long indebtedness_id;
	
	/** 近6个月银行流水（主要银行）  */
	private Long capital_flow_id;

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
