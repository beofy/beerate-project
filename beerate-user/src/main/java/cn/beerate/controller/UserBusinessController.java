package cn.beerate.controller;

import cn.beerate.PropertiesHolder;
import cn.beerate.common.Message;
import cn.beerate.dao.ICustomQueryDsl;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.InvestPrefer;
import cn.beerate.model.dto.UserBusiness;
import cn.beerate.model.entity.Qt_user_business;
import cn.beerate.model.entity.t_user_business;
import cn.beerate.oss.OSS;
import cn.beerate.service.UserBusinessService;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import org.apache.commons.lang3.StringUtils;
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
    private ICustomQueryDsl iCustomQueryDsl;

    public UserBusinessController(UserBusinessService userBusinessService, OSS oss, ICustomQueryDsl iCustomQueryDsl) {
        this.userBusinessService = userBusinessService;
        this.oss = oss;
        this.iCustomQueryDsl = iCustomQueryDsl;
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

            return Message.ok("上传成功");

        } catch (IOException e) {
            return Message.success("解析名片文件异常，请重新上传");
        }

    }

    /**
     * 补充名片信息
     */
    @PostMapping("/supplement")
    public Message<String> supplementUserBusiness(InvestPrefer investPrefer, String aboutText, String workText) {

        if (investPrefer == InvestPrefer.NONE) {
            return Message.error("请选择投资偏好");
        }

        if (StringUtils.isBlank(aboutText)) {
            return Message.error("请填写个人介绍");
        }

        if (StringUtils.isBlank(workText)) {
            return Message.error("请填写工作经历");
        }

        t_user_business userBusiness = userBusinessService.getOne(Qt_user_business.t_user_business.user.id.eq(getUserId()));
        if (userBusiness == null) {
            return Message.error("名片信息不存在");
        }

        if (userBusiness.getAuditStatus() == AuditStatus.WAIT_AUDIT) {
            return Message.error("名片信息审核中");
        }

        userBusiness.setInvestPrefer(investPrefer);
        userBusiness.setAboutText(aboutText);
        userBusiness.setWorkText(workText);
        userBusiness.setAuditStatus(AuditStatus.WAIT_AUDIT);

        userBusinessService.save(userBusiness);

        return Message.ok("提交成功");
    }

    /**
     * 名片信息
     */
    @PostMapping("/detail")
    public Message<UserBusiness> detail() {
        Qt_user_business qtUserBusiness = Qt_user_business.t_user_business;

        Expression<UserBusiness> expression = Projections.constructor(UserBusiness.class
                ,qtUserBusiness.name
                ,qtUserBusiness.company
                ,qtUserBusiness.department
                ,qtUserBusiness.title
                ,qtUserBusiness.telCell
                ,qtUserBusiness.telWork
                ,qtUserBusiness.address
                ,qtUserBusiness.email
                ,qtUserBusiness.businessCardUri
                ,qtUserBusiness.investPrefer
                ,qtUserBusiness.aboutText
                ,qtUserBusiness.workText
                ,qtUserBusiness.auditStatus
                ,qtUserBusiness.verifyTime
                );
        Predicate predicate = qtUserBusiness.user.id.eq(1L);

        UserBusiness userBusiness = iCustomQueryDsl.getOne(expression,qtUserBusiness,predicate);

        return Message.success(userBusiness);
    }
}
