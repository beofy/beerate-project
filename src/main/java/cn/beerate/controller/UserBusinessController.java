package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.model.InvestPrefer;
import cn.beerate.model.dto.UserBusiness;
import cn.beerate.model.entity.t_user_business;
import cn.beerate.oss.OSS;
import cn.beerate.service.UserBusinessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@RestController
@RequestMapping("/user/business")
public class UserBusinessController extends UserBaseController {

    private UserBusinessService userBusinessService;
    private OSS oss;

    public UserBusinessController(UserBusinessService userBusinessService, OSS oss) {
        this.userBusinessService = userBusinessService;
        this.oss = oss;
    }

    /**
     * 上传用户名片
     */
    @PostMapping("/upload")
    public Message<String> uploadUserBusiness(MultipartFile file) {
        if (file == null) {
            return Message.error("请上传用户文件");
        }

        try (InputStream inputStream = file.getInputStream()) {

            t_user_business userBusiness = userBusinessService.parse(inputStream);
            userBusiness.setBusinessCardUri("/" + PropertiesHolder.properties.getFileProperties().getUserFile() + UUID.randomUUID().toString());

            //保存名片信息
            userBusinessService.addBusiness(userBusiness, getUserId());

            //保存名片文件
            oss.uploadFile(inputStream, oss.getRoot() + userBusiness.getBusinessCardUri());

            return Message.ok("上传成功，请补充名片资料");

        } catch (IOException e) {
            return Message.success("解析名片文件异常，请重新上传");
        }

    }

    /**
     * 补充名片信息
     */
    @PostMapping("/supplement")
    public Message<String> supplementUserBusiness(InvestPrefer investPrefer, String aboutText, String workText) {

        return userBusinessService.supplementUserBusiness(investPrefer, aboutText, workText, getUserId());
    }

    /**
     * 名片信息
     */
    @PostMapping("/detail")
    public Message<UserBusiness> detail() {
        return userBusinessService.findUserBusinessDetail(getUserId());

    }
}
