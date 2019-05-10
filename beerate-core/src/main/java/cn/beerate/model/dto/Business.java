package cn.beerate.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Business {
    private String name;
    private String request_id;
    private Boolean success;
    private List<String> addr;
    private List<String> company;
    private List<String> department;
    private List<String> email;
    private List<String> tel_cell;
    private List<String> tel_work;
    private List<String> title;


}
