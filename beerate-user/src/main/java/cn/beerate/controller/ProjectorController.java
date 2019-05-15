package cn.beerate.controller;

import cn.beerate.service.UserBusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/projector")
public class ProjectorController {

    private UserBusinessService userBusinessService;

    public ProjectorController(UserBusinessService userBusinessService) {
        this.userBusinessService = userBusinessService;
    }


    @GetMapping("/list")
    public Message<>
}
