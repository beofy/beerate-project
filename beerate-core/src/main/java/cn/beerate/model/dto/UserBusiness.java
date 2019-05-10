package cn.beerate.model.dto;



import lombok.Data;

import java.util.Date;

@Data
public class UserBusiness {

    private String name;

    private String company;

    private String department;

    private String title;

    private String telCell;

    private String telWork;

    private String address;

    private String email;

    private String businessCardUri;

    private String investPrefer;

    private String aboutText;

    private String workText;

    private String auditStatus;

    private Date verifyTime;

    public UserBusiness(String name, String company, String department, String title, String telCell, String telWork, String address, String email, String businessCardUri, String investPrefer, String aboutText, String workText, String auditStatus, Date verifyTime) {
        this.name = name;
        this.company = company;
        this.department = department;
        this.title = title;
        this.telCell = telCell;
        this.telWork = telWork;
        this.address = address;
        this.email = email;
        this.businessCardUri = businessCardUri;
        this.investPrefer = investPrefer;
        this.aboutText = aboutText;
        this.workText = workText;
        this.auditStatus = auditStatus;
        this.verifyTime = verifyTime;
    }
}
