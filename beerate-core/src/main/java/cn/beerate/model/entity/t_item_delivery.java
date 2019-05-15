package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_delivery", comment = "项目投递表")
public class t_item_delivery extends Model {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private t_user user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_user_id")
    private t_user delivery_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_loan_id")
    private t_item_loan item_loan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_ipo_id")
    private t_item_pre_ipo pre_ipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_pledge_id")
    private t_item_stock_pledge stock_pledge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_transfer_id")
    private t_item_stock_transfer stock_transfer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_trade_id")
    private t_item_block_trade block_trade;

}
