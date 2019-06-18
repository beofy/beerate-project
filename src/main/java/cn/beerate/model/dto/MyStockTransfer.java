package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MyStockTransfer {

    @Id
    private long id;

    private String bidName;

    private Boolean isQuoted;

    private String industryRealm;

    private Double transferAmount;

    private String auditStatus;

}
