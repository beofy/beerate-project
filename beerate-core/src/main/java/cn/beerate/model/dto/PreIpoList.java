package cn.beerate.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PreIpoList {

    public PreIpoList(long id, String preIpoName, String industryRealm, String ratchetTerms, Boolean isNewThirdBoardListing, Date iPOBaseDate, String auditStatus) {
        this.id = id;
        this.preIpoName = preIpoName;
        this.industryRealm = industryRealm;
        this.ratchetTerms = ratchetTerms;
        this.isNewThirdBoardListing = isNewThirdBoardListing;
        this.iPOBaseDate = iPOBaseDate;
        this.auditStatus = auditStatus;
    }

    private long id;

    private String preIpoName;

    private String industryRealm;

    private String ratchetTerms;

    private Boolean isNewThirdBoardListing;

    private Date iPOBaseDate;

    private String auditStatus;
}
