package cn.beerate.model.dto;

import lombok.Data;

@Data
public class ProjectorIntro {

    public ProjectorIntro(String name, String company, String title) {
        this.name = name;
        this.company = company;
        this.title = title;
    }

    private String name;

    private String company;

    private String title;

}
