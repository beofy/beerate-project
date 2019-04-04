package cn.beerate.controller;

import cn.beerate.common.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    /**
     * 管理员注册
     */
    public Message<String> regist(){

        return null;
    }
}
