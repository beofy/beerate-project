package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class StockTransfer {

    @Id
    private Long id;

    private String bidName;

    private Boolean companyNameIsPublic;

    private String companyName;

    public Object getCompanyName() {
        return companyNameIsPublic?companyName:"******";
    }

    private String contact;

    private Boolean isQuoted;

    private Double currentValuation;

    private Double transferAmount;

    private Boolean isPrivacyEquityRatio;

    public Object getEquityRatio(){
        return isPrivacyEquityRatio?"******":equityRatio;
    }

    private Double equityRatio;

}
