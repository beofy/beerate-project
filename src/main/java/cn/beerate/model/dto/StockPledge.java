package cn.beerate.model.dto;

import cn.beerate.model.LoanPeriod;
import cn.beerate.model.StockBlock;
import cn.beerate.model.StockNature;
import lombok.Data;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class StockPledge {

    @Id
    private Long id;

    private String financingBody;

    private String stockCode;

    private String stockNature;

    public void setStockNature(StockNature stockNature) {
        this.stockNature = stockNature.name();
    }

    public StockNature getStockNature(){
        return EnumUtils.getEnumIgnoreCase(StockNature.class,stockNature);
    }

    private String stockBlock;

    public void setStockBlock(StockBlock stockBlock) {
        this.stockBlock = stockBlock.name();
    }

    public StockBlock getStockBlock(){
        return EnumUtils.getEnumIgnoreCase(StockBlock.class,stockBlock);
    }

    private Double loanAmount;

    private Double pledgeRates;

    private String loanPeriod;

    public void setLoanPeriod(LoanPeriod loanPeriod) {
        this.loanPeriod = loanPeriod.name();
    }

    public LoanPeriod getLoanPeriod(){
        return EnumUtils.getEnumIgnoreCase(LoanPeriod.class,loanPeriod);
    }

}
