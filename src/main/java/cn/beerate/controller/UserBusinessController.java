package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.model.ItemType;
import cn.beerate.model.entity.t_user_business;
import cn.beerate.service.UserBusinessService;
import cn.beerate.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/user/business")
public class UserBusinessController extends UserBaseController {
    private final Log logger = LogFactory.getLog(this.getClass());

    private UserBusinessService userBusinessService;

    public UserBusinessController(UserBusinessService userBusinessService) {
        this.userBusinessService = userBusinessService;
    }

    /**
     * 上传用户名片
     */
    @PostMapping("/upload")
    public Message<String> uploadUserBusiness(MultipartFile file) {
        if (file == null) {
            return Message.error("请上传用户文件");
        }

        //保存名片信息
        t_user_business userBusiness;
        try (InputStream inputStream = file.getInputStream()) {
            userBusiness = userBusinessService.parse(inputStream);
            userBusiness.setBusinessCardUri(PropertiesHolder.ATTACHMENT_PATH + file.getOriginalFilename());
            Message<t_user_business> message = userBusinessService.addUserBusiness(userBusiness, getUserId());
            if (message.fail()){
                return Message.error(message.getMsg());
            }
        } catch (IOException e) {
            logger.error(String.format("解析名片文件异常,原因：[%s]",e.getCause()),e);
            return Message.error("解析名片文件异常");
        }

        //保存名片文件
        try {
            file.transferTo(new File(PathUtil.getRoot() + userBusiness.getBusinessCardUri()));
        } catch (IOException e) {
            logger.error(String.format("名片上传异常,原因：[%s]",e.getCause()),e);
            return Message.error("名片上传异常");
        }

        return Message.ok("上传成功，请补充名片资料");
    }

    /**
     * 补充名片信息
     */
    @PostMapping("/supplement")
    public Message<String> supplementUserBusiness(ItemType investPrefer, String aboutText, String workText) {
        if (investPrefer == ItemType.NONE) {
            return Message.error("请选择投资偏好");
        }

        if (StringUtils.isBlank(aboutText)) {
            return Message.error("请填写个人介绍");
        }

        if (StringUtils.isBlank(workText)) {
            return Message.error("请填写工作经历");
        }

        Message<t_user_business> message = userBusinessService.supplementUserBusiness(investPrefer, aboutText, workText, getUserId());
        if (message.fail()){
            return Message.error(message.getMsg());
        }

        return Message.ok("补充成功");
    }

    /**
     * 名片信息
     */
    @PostMapping("/detail")
    public Message detail() {
        return userBusinessService.findUserBusinessDetail(getUserId());
    }
}
