package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Loan {

    @Id
    private Long id;

    private String itemName;

    private String companyName;

    private Boolean isQuoted;

    private String stockCode;

    private Double amount;

    private Integer period;

    private String periodUnit;

    private Boolean isBp;

}
