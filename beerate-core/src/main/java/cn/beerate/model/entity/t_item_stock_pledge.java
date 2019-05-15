package cn.beerate.model.entity;

import cn.beerate.model.ItemModel;
import cn.beerate.model.LoanPeriod;
import cn.beerate.model.StockBlock;
import cn.beerate.model.StockNature;
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
@org.hibernate.annotations.Table(appliesTo = "t_item_stock_pledge", comment = "股票质押表")
public class t_item_stock_pledge extends ItemModel {

    @Column(columnDefinition = "varchar(128) not null default '' comment '融资主体'")
    private String financingBody;

    @Column(columnDefinition = "varchar(6) not null default '' comment '股票代码'")
    private String stockCode;

    @Column(columnDefinition = "varchar(12) not null default '' comment '质押股票流通性质'")
    private String stockNature;

    public void setStockNature(StockNature stockNature) {
        this.stockNature = stockNature.name();
    }

    public StockNature getStockNature(){
        return EnumUtils.getEnumIgnoreCase(StockNature.class,stockNature);
    }

    @Column(columnDefinition = "datetime default null comment '限售到期日'")
    private Date salesDeadline;

    @Column(columnDefinition = "varchar(30) not null default '' comment '质押股票所属板块'")
    private String stockBlock;

    public void setStockBlock(StockBlock stockBlock) {
        this.stockBlock = stockBlock.name();
    }

    public StockBlock getStockBlock(){
        return EnumUtils.getEnumIgnoreCase(StockBlock.class,stockBlock);
    }

    @Column(columnDefinition = "bit(1) default null default 0 comment '融资人是否为上市公司控股股东或实际控制人'")
    private Boolean isQuoteOrActualController;

    @Column(columnDefinition = "int(12) not null default '0' comment '总持有股票数'")
    private Integer holdStockShares;

    @Column(columnDefinition = "int(12) not null default '0' comment '质押股票数'")
    private Integer pledgeStockShares;

    @Column(columnDefinition = "int(12) not null default '0' comment '剩余可质押股票数'")
    private Integer surplusPledgeStockShares;

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '融资金额'")
    private Double loanAmount;

    @Column(columnDefinition = "double(5,2) not null default '0.00' comment '质押率'")
    private Double pledgeRates;

    @Column(columnDefinition = "varchar(30) not null default '0' comment '融资期限'")
    private String loanPeriod;

    public void setLoanPeriod(LoanPeriod loanPeriod) {
        this.loanPeriod = loanPeriod.name();
    }

    public LoanPeriod getLoanPeriod(){
        return EnumUtils.getEnumIgnoreCase(LoanPeriod.class,loanPeriod);
    }

    @Column(columnDefinition = "double(5,2) not null default '0.00' comment '融资方愿意支付的融资成本'")
    private Double financingPartyPaysCostRates;

    @Column(columnDefinition = "varchar(255) not null default '' comment '最近一个年度盈利情况'")
    private String recentOneYearProfitsDescription;

    @Column(columnDefinition = "varchar(255) not null default '' comment '借款用途说明'")
    private String purpose;

    @Column(columnDefinition = "varchar(255) not null default '' comment '还款来源说明'")
    private String repaymentDescription;

    @Column(columnDefinition = "varchar(255) not null default '' comment '其他增信措施'")
    private String enhancementConfidenceMeasures;

    //==========================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private t_user user;

    //==========================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private t_admin admin;

    //==========================================
    @OneToMany
    @JoinColumn(name = "stock_pledge_id")
    private List<t_item_message_board> message_boards;

    //==========================================

    @OneToMany(mappedBy = "stock_pledge")
    private List<t_item_collect> itemCollects;
}
