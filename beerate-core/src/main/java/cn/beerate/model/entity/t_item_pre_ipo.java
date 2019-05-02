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
@org.hibernate.annotations.Table(appliesTo = "t_item_pre_ipo",comment="PRE_IPO表")
public class t_item_pre_ipo extends Model {

    /** 是否新三板挂牌 */
    private Boolean isNewThirdBoardListing;

    /** 项目名称 */
    private String preipoName;

    /*  ---已挂牌--- */

    /** 股票代码 */
    private String stockCode;

    /** 是否主承销券商 */
    private Boolean isPrincipalUnderwriter;

    /** 辅导券商是否已进场 */
    private Boolean isTutoringBrokerage;

    /** 辅导券商名称 */
    private String TutoringBrokerageName;

    /*  ---已挂牌--- */

    /*  ---未挂牌--- */
    /**  标的名称 */
    private String bidName;

    /**  标的企业名称是否公开 */
    private Boolean companyNameIspublic;

    /**  标的企业名称 */
    private String companyName;

    /**  所在省市 */
    private String city;
    /*  ---未挂牌--- */

    /**  标的所处行业 */
    private Integer realm;

    /** 拟IPO基准日 */
    private Date iPOBaseDate;

    /** 对赌条件
     * 1、无对赌
     2、回购对赌
     3、业绩对赌
     */
    private Integer ratchetTerms;

    /** 对赌描述 */
    private String ratchetTermsDescription;

    /*  ---已挂牌--- */
    /** 价格是否面议 */
    private Boolean isPriceNegotiable;

    /** 意向价格 */
    private Double intentionalPrice;

    /** 交易股数 */
    private Integer exchangeShares;

    /**  份额规模 */
    private Double sharesAmount;

    /** 参与门槛 */
    private Double thresholdAmount;
    /* ---已挂牌--- */

    /* ---未挂牌--- */

    /**  选择币种  */
    private Integer currency;

     /**  融资金额 */
    private Double loanAmount;

     /**  意向估值 */
    private Double IntentionValuation;

     /**  去年净利润 */
    private Double lastYearProfits;

     /**  融资期限 */
    private Integer loanPeriod;

     /**  融资用途 */
    private String purpose;

     /**  投资亮点 */
    private String investLightSpot;
    /*  ---未挂牌--- */


    /** BP\商业计划书 */
    private String businessProposal;

    /** 项目截至日期 */
    private Date endTime;

    /** 是否加急 */
    private Boolean isUrgent;

    /** 查看是否需要平台认证 */
    private Boolean isPlatformAuthentication;

    /** 是否一手 */
    private Boolean isFirstHandle;

    /** 是否前台展示 */
    private Boolean isShow;

    /** 联系人 */
    private String contact;

    /** 联系人电话 */
    private String contactMobile;

    /** 内容描述 */
    private String contentDescription;


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
    @JoinColumn(name = "pre_ipo_id")
    private List<t_item_message_board> message_boards;



}
