package cn.beerate.model.entity;

import cn.beerate.model.Currency;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.ItemModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@org.hibernate.annotations.Table(appliesTo = "t_item_stock_transfer",comment="老股转让表")
public class t_item_stock_transfer extends ItemModel {

    @Column(columnDefinition = "varchar(128) not null default '' comment '标的名称'")
    private String bidName;

    @Column(columnDefinition = " bit(1) not null default 0 COMMENT '标的所属阶段'")
    private Boolean isQuoted;

    @Column(columnDefinition = " bit(1) not null default 0 COMMENT '企业名称是否公开'")
    private Boolean companyNameIsPublic;

    @Column(columnDefinition = "varchar(128) not null default '' comment '企业名称'")
    private String companyName;

    @Column(columnDefinition = "varchar(255) not null default '' comment '行业领域'")
    private String industryRealm;

    public void setIndustryRealm(IndustryRealm industryRealm) {
        this.industryRealm = industryRealm.name();
    }

    @Column(columnDefinition = "varchar(12) not null default '' comment '币种'")
    private String currency;

    public void setCurrency(Currency currency) {
        this.currency = currency.name();
    }

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '去年净利润'")
    private Double lastYearProfits;

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '本轮估值'")
    private Double currentValuation;

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '转让金额'")
    private Double transferAmount;

    @Column(columnDefinition = " bit(1) not null default 1 COMMENT '转让股权比例是否保密'")
    private boolean isPrivacyEquityRatio;

    @Column(columnDefinition = "double(3,2) not null default '0.00' comment '转让股权比例'")
    private Double equityRatio;

    @Column(columnDefinition = "varchar(255) not null default '' comment '投资亮点'")
    private String investLightspot;

    @Column(columnDefinition = "varchar(15) not null default '' comment '联系人'")
    private String contact;

    @Column(columnDefinition = "varchar(15) not null default '' comment '联系人电话'")
    private String contactMobile;

    @Column(columnDefinition = "varchar(15) not null default '' comment '内容描述'")
    private String contentDescription;

    @Column(columnDefinition = "datetime default null comment '项目结束时间'")
    private Date endTime;

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
    @JoinColumn(name = "stock_transfer_id")
    private List<t_item_message_board> message_boards;

}
