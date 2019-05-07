package cn.beerate.model.entity;

import cn.beerate.model.Model;
import cn.beerate.model.NetProfit;
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
@org.hibernate.annotations.Table(appliesTo = "t_item_overseas_listing",comment="海外上市表")
public class t_item_overseas_listing extends Model {

	@Column(columnDefinition = "varchar(12) not null default '' comment '真实姓名'")
	private String realName;
	
	@Column(columnDefinition = "varchar(15) not null default '' comment '手机号'")
	private String mobile;
	
	@Column(columnDefinition = "varchar(20) not null default '' comment '职位'")
	private String job;
	
	@Column(columnDefinition = "varchar(64) not null default '' comment '企业名称'")
	private String companyName;
	
	@Column(columnDefinition = "varchar(32) not null default '' comment '企业主营业务'")
	private String companyBusiness;
	
	@Column(columnDefinition = "varchar(12) not null default '' comment '企业全年净利润情况（单位：人民币）'")
	private String netProfit;
	public void setNetProfit(NetProfit netProfit){
		this.netProfit=netProfit.name();
	}
	public NetProfit getNetProfit(){
		return EnumUtils.getEnumIgnoreCase(NetProfit.class,netProfit);
	}

	@Column(columnDefinition = "varchar(255) not null default '' comment '备注'")
	private String mark;

	@Column(columnDefinition = "bit(1) not null default 0 comment '前台是否展示'")
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
