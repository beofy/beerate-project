package cn.beerate.model.dto;

import lombok.Data;

@Data
public class ProjectorList {

    private long id;

    private String name;

    private String company;

    private String title;

    private Integer visitorTotals;

    private Integer receiveItemTotals;

}
