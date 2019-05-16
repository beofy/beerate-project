package cn.beerate.model.dto;

import lombok.Data;

@Data
public class ProjectorList {

    public ProjectorList(long userId, String name, String company, String title, long visitorTotals, long receiveItemTotals) {
        this.userId = userId;
        this.name = name;
        this.company = company;
        this.title = title;
        this.visitorTotals = visitorTotals;
        this.receiveItemTotals = receiveItemTotals;
    }

    private long userId;

    private String name;

    private String company;

    private String title;

    private long visitorTotals;

    private long receiveItemTotals;

}
