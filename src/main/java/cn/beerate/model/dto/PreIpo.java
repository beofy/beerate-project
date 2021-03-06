package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class PreIpo {

    @Id
    private Long id;

    private String preIpoName;

    private Boolean isNewThirdBoardListing;

    private String industryRealm;

    private Date iPOBaseDate;

    private String ratchetTerms;

}
