package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ProjectorIntro {

    @Id
    private Long id;

    private String name;

    private String company;

    private String title;

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
