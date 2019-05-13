package cn.beerate.model.entity;

import cn.beerate.model.*;
import cn.beerate.model.IndustryRealm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_pre_ipo", comment = "PRE_IPO表")
public class t_item_pre_ipo extends ItemModel {

    @Column(columnDefinition = "varchar(128) not null default '' comment '项目名称'")
    private String preIpoName;

    @Column(columnDefinition = "bit not null default 0 comment '是否新三板挂牌'")
    private Boolean isNewThirdBoardListing;

    /*  ====================已挂牌==================== */
    @Column(columnDefinition = "varchar(6) not null default '' comment '股票代码'")
    private String stockCode;

    @Column(columnDefinition = "bit not null default 0 comment '是否主承销券商'")
    private Boolean isPrincipalUnderwriter;

    @Column(columnDefinition = "bit not null default 0 comment '辅导券商是否已进场'")
    private Boolean isTutoringBrokerage;

    @Column(columnDefinition = "varchar(128) not null default '' comment '辅导券商名称'")
    private String TutoringBrokerageName;
    /*  ====================已挂牌====================*/

    /*  ====================未挂牌==================== */
    @Column(columnDefinition = "varchar(128) not null default '' comment '标的名称'")
    private String bidName;

    @Column(columnDefinition = "bit not null default 0 comment '标的企业名称是否公开'")
    private Boolean companyNameIsPublic;

    @Column(columnDefinition = "varchar(128) not null default '' comment '标的企业名称'")
    private String companyName;

    @Column(columnDefinition = "varchar(12) not null default '' comment '所在省市'")
    private String city;
    /*  ====================未挂牌==================== */

    @Column(columnDefinition = "varchar(30) not null default '' comment '标的所处行业领域'")
    private String industryRealm;

    public void setIndustryRealm(IndustryRealm industryRealm) {
        this.industryRealm = industryRealm.name();
    }

    public IndustryRealm getIndustryRealm(){
       return EnumUtils.getEnumIgnoreCase(IndustryRealm.class,industryRealm);
    }

    @Column(columnDefinition = "datetime  default null comment '拟IPO基准日'")
    private Date iPOBaseDate;

    @Column(columnDefinition = "varchar(12) not null default '' comment '对赌条件'")
    private String ratchetTerms;

    public void setRatchetTerms(RatchetTerms ratchetTerms){
        this.ratchetTerms=ratchetTerms.name();
    }

    public RatchetTerms getRatchetTerms(){
        return EnumUtils.getEnumIgnoreCase(RatchetTerms.class,ratchetTerms);
    }

    @Column(columnDefinition = "varchar(255) not null default '' comment '对赌描述'")
    private String ratchetTermsDescription;

    /*  ====================已挂牌==================== */
    @Column(columnDefinition = "bit(1) not null default 0 comment '价格是否面议'")
    private Boolean isPriceNegotiable;

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '意向价格'")
    private Double intentionalPrice;

    @Column(columnDefinition = "bigint(12) not null default '0' comment '交易股数'")
    private Integer exchangeShares;

    @Column(columnDefinition = "double(12,2) not null default '0.00' comment '份额规模'")
    private Double sharesAmount;

    @Column(columnDefinition = "double(12,2) not null default '0.00' comment '参与门槛'")
    private Double thresholdAmount;
    /* ====================已挂牌==================== */

    /* ====================未挂牌==================== */

    @Column(columnDefinition = "varchar(12) not null default '' comment '币种'")
    private String currency;

    public void setCurrency(Currency currency) {
        this.currency = currency.name();
    }

    public Currency getCurrency(){
        return EnumUtils.getEnumIgnoreCase(Currency.class,currency);
    }

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '融资金额'")
    private Double loanAmount;

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '意向估值'")
    private Double IntentionValuation;

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '去年净利润'")
    private Double lastYearProfits;

    @Column(columnDefinition = "varchar(30) not null default '' comment '融资期限'")
    private String loanPeriod;

    public void setLoanPeriod(LoanPeriod loanPeriod){
        this.loanPeriod=loanPeriod.name();
    }

    public LoanPeriod getLoanPeriod(){
        return EnumUtils.getEnumIgnoreCase(LoanPeriod.class,loanPeriod);
    }

    @Column(columnDefinition = "varchar(255) not null default '' comment '融资用途'")
    private String purpose;

    @Column(columnDefinition = "varchar(255) not null default '' comment '投资亮点'")
    private String investLightSpot;
    /*  ====================未挂牌==================== */

    @Column(columnDefinition = "varchar(255) not null default '' comment 'BP计划书'")
    private String businessProposalUri;

    @Column(columnDefinition = "varchar(12) not null default '' comment '联系人'")
    private String contact;

    @Column(columnDefinition = "varchar(20) not null default '' comment '联系人电话'")
    private String contactMobile;

    @Column(columnDefinition = "varchar(255) not null default '' comment '内容描述'")
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
