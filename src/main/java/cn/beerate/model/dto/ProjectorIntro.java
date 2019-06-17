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

}
