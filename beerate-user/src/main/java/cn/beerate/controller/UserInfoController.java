package cn.beerate.controller;

import cn.beerate.common.Message;
import cn.beerate.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/userinfo")
public class UserInfoController extends UserBaseController{

    private UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/update")
    public Message<String> updateUserInfo(){

        return Message.ok("更新用户信息成功");
    }

}
