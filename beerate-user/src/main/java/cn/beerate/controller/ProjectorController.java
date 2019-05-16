package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.service.ProjectorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 项目方控制器
 */
@RestController
@RequestMapping("/user/projector")
public class ProjectorController {

    private ProjectorService projectorService;
    public ProjectorController(ProjectorService projectorService) {
        this.projectorService = projectorService;
    }


    /**
     * 项目列表
     * @return
     */
    @GetMapping("/list")
    public Message list(){
        return null;
    }

    /**
     * 项目简介
     */
    @PostMapping("/intro")
    public Message intro(){
        return null;
    }


    /**
     * @// TODO: 2019/5/16  项目方功能待完善 
     */


    /**
     * 项目详细信息
     */
    @PostMapping("/detail")
    public Message detail(){
        return null;
    }
}
