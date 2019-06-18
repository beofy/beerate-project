package cn.beerate.model.dto;

import cn.beerate.model.ItemType;
import lombok.Data;
import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Entity;

@Data
@Entity
public class ProjectorDetail {

    private Long id;

    private String name;

    private String company;

    private String department;

    private String title;

    private String telCell;

    private String telWork;

    private String address;

    private String email;

    private String investPrefer;

    public void setInvestPrefer(ItemType investPrefer){
        this.investPrefer=investPrefer.name();
    }

    public ItemType getInvestPrefer(){
        return EnumUtils.getEnumIgnoreCase(ItemType.class,this.investPrefer);
    }

    private String aboutText;

    private String workText;


    //关注
    private long attentionTotals;

    //被关注
    private long beAttentionTotals;

    //联系
    private long contactTotals;

    //被联系
    private long beContactTotals;

    //访客数
    private long visitorTotals;

    //收到项目
    private long acceptItemTotals;

}
