package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MyStockPledge {

    @Id
    private long id;

    private String financingBody;

    private String stockCode;

    private String stockNature;

    private String stockBlock;

    private Integer holdStockShares;

    private String auditStatus;
}
