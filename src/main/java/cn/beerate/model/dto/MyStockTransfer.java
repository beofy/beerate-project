package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class MyStockTransfer {

    private long id;

    private String bidName;

    private Boolean isQuoted;

    private String industryRealm;

    private Double transferAmount;

    private String auditStatus;

}
