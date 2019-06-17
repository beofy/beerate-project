package cn.beerate.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Projector {

    @Id
    private long userId;

    private String name;

    private String company;

    private String title;

    private long visitorTotals;

    private long receiveItemTotals;

}
