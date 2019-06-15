package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class LoanList {


    @Id
    private long id;

    private String itemName;

    private String industryRealm;

    private Double amount;

    private Integer period;

    private String auditStatus;

}
