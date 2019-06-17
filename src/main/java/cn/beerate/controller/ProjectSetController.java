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
@RequestMapping("/user/projectSet")
public class ProjectSetController {

    private ProjectorService projectorService;
    public ProjectSetController(ProjectorService projectorService) {
        this.projectorService = projectorService;
    }

    /**
     * 项目列表
     */
    @GetMapping("/list")
    public Message list(){
        return null;
    }

    /**
     * 项目方简介
     */
    @PostMapping("/intro")
    public Message intro(){
        return null;
    }

    /**
     * 项目详细信息
     */
    @PostMapping("/detail")
    public Message detail(){
        return null;
    }
}
