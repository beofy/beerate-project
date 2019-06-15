package cn.beerate.model.dto;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class UserBusiness {
    @Id
    private Long id;

    private Date createTime;

    private Date updateTime;

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

}
