package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class MyPreIpo {

    private long id;

    private String preIpoName;

    private String industryRealm;

    private String ratchetTerms;

    private Boolean isNewThirdBoardListing;

    private Date iPOBaseDate;

    private String auditStatus;
}
