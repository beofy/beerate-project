package cn.beerate.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdminRight {

    long adminId;

    List<Long> rightIds;

}