package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_stock_pledge",comment="股票质押表")
public class t_item_stock_pledge extends Model {

     /** 融资主体 */
     private String financingBody;

     /** 股票代码 */
     private String stockCode;

     /** 质押股票流通性质（1--流通 2--限售 3--禁售） */
     private Integer stockPledgeNature;

     /** 限售到期日 */
     private Date salesDeadline;

     /**
      * 质押股票所属板块<br/>
      * 1、沪深300成分股
      * 2、主板其他
      * 3、中小板
      * 4、创业板
      */
     private Integer stockPledgeBoard;

     /** 融资人是否为上市公司控股股东或实际控制人 */
     private Integer isQuoteOrActualController;

     /** 总持有股票数 */
     private Integer holdStockShares;

     /** 质押股票数 */
     private Integer pledgeStockShares;

     /** 剩余可质押股票数 */
     private Integer surplusPledgeStockShares;

     /** 融资金额 */
     private Double loanAmount;

     /** 质押率 */
     private Double pledgeRates;

     /** 融资期限 */
     private Integer loanPeriod;

     /** 融资方愿意支付的融资成本 */
     private Double financingPartyPaysCostRates  ;

     /** 最近一个年度盈利情况 */
     private String recentOneYearProfitsDescription;

     /** 借款用途说明 */
     private String purpose;

     /** 还款来源说明 */
     private String repaymentDescription;

    /** 其他增信措施 */
     private String enhancementConfidenceMeasures;

    /** 项目结束时间 */
    private Date endTime;

    /** 是否加急 */
    private Boolean isUrgent;

    /** 查看是否需要平台认证 */
    private Boolean isPlatformAuthentication;

    /** 是否一手 */
    private Boolean isFirstHandle;

    /** 是否前台展示 */
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
    @JoinColumn(name = "stock_pledge_id")
    private List<t_item_message_board> message_boards;
}
