package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class MyStockPledge {

    private long id;

    private String financingBody;

    private String stockCode;

    private String stockNature;

    private String stockBlock;

    private Integer holdStockShares;

    private String auditStatus;
}
