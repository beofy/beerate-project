package cn.beerate.model.entity;

import cn.beerate.model.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_collect",comment="项目收藏")
public class t_item_collect extends Model {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private t_user user;

    @ManyToOne
    @JoinColumn(name = "item_loan_id")
    private t_item_loan item_loan;

    @ManyToOne
    @JoinColumn(name = "block_trade_id")
    private t_item_block_trade block_trade;

    @ManyToOne
    @JoinColumn(name = "pre_ipo_id")
    private t_item_pre_ipo pre_ipo;

    @ManyToOne
    @JoinColumn(name = "stock_pledge_id")
    private t_item_stock_pledge stock_pledge;

    @ManyToOne
    @JoinColumn(name = "stock_transfer_id")
    private t_item_stock_transfer stock_transfer;

}
