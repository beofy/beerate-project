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
@org.hibernate.annotations.Table(appliesTo = "t_item_overseas_listing",comment="海外上市表")
public class t_item_overseas_listing extends Model {

	/** 真实姓名 */
	private String realName;
	
	/** 手机号 */
	private String mobile;
	
	/** 职位 */
	private String job;
	
	/** 企业名称 */
	private String company_name;
	
	/** 企业主营业务 */
	private String company_business;
	
	/** 企业全年净利润情况（单位：人民币） */
	private int netprofit;

	private String mark;

	private Boolean isShow;

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
    @JoinColumn(name = "overseas_listing_id")
    private List<t_item_message_board> message_boards;
}
