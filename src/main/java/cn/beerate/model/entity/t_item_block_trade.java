package cn.beerate.model.entity;

import cn.beerate.model.CreditIdentification;
import cn.beerate.model.ItemModel;
import cn.beerate.model.UnderWeightIdentification;
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
@org.hibernate.annotations.Table(appliesTo = "t_item_block_trade",comment="大宗交易表")
public class t_item_block_trade extends ItemModel {

    @Column(columnDefinition = "varchar(128) not null default '' comment '项目名称'")
    private String blockTradeName;

    @Column(columnDefinition = "varchar(6) not null default '' comment '股票代码'")
    private String stockCode;

    @Column(columnDefinition = "double(5,2) not null default '0.00' comment '交易折价'")
    private Double exchangeRate;

    @Column(columnDefinition = "int(12) not null default '0' comment '减持股数'")
    private Integer underweightShares;

    @Column(columnDefinition = "decimal(12,2) not null default '0.00' comment '减持总金额'")
    private Double underweightAmount;

    @Column(columnDefinition = "varchar(30) not null default '' comment '减持方身份'")
    private  String underweightIdentification;

    public void setUnderweightIdentification(UnderWeightIdentification underweightIdentification) {
        this.underweightIdentification = underweightIdentification.name();
    }

    public UnderWeightIdentification getUnderWeightIdentification(){
        return EnumUtils.getEnumIgnoreCase(UnderWeightIdentification.class,this.underweightIdentification);
    }

    @Column(columnDefinition = "bit(1) not null default 0 comment '是否增信'")
    private Boolean isConfidence;

    @Column(columnDefinition = "double(5,2) not null default '0.00' comment '预期收益率（增信）'")
    private Double expectedReturn;

    @Column(columnDefinition = "datetime  default null comment '增信期限（增信）'")
    private Date confidencePeriod;

    @Column(columnDefinition = "varchar(30) not null default '' comment '增信身份（增信）'")
    private String creditIdentification;

    public void setCreditIdentification(CreditIdentification creditIdentification) {
        this.creditIdentification = creditIdentification.name();
    }

    public CreditIdentification getCreditIdentification(){
        return EnumUtils.getEnumIgnoreCase(CreditIdentification.class,creditIdentification);
    }

    @Column(columnDefinition = "int(12) not null default '0' comment '增信方超额收益分成（增信）'")
    private  Integer confidenceShare;

    @Column(columnDefinition = "bit(1) not null default 0 comment '增信是否公开（增信）'")
    private Boolean confidenceIsPublic;

}
