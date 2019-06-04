package cn.beerate.service.impl;

import cn.beerate.model.AuditStatus;
import cn.beerate.model.InvestPrefer;
import cn.beerate.model.bean.Business;
import cn.beerate.model.dto.UserBusiness;
import cn.beerate.model.entity.Qt_user_business;
import cn.beerate.model.entity.t_user;
import cn.beerate.utils.BcrUtil;
import cn.beerate.common.Message;
import cn.beerate.dao.UserBusinessDao;
import cn.beerate.model.entity.t_user_business;
import cn.beerate.service.UserBusinessService;
import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
@Transactional(readOnly = true)
public class UserBusinessServiceImpl extends BaseServiceImpl<t_user_business> implements UserBusinessService {

    private UserBusinessDao userBusinessDao;
    public UserBusinessServiceImpl(UserBusinessDao userBusinessDao) {
        super(userBusinessDao);
        this.userBusinessDao = userBusinessDao;
    }

    public t_user_business parse(InputStream inputStream) {

        String result = BcrUtil.parse(inputStream);
        Business business = JSONObject
                .parseObject(result)
                .getJSONArray("outputs")
                .getJSONObject(0)
                .getJSONObject("outputValue")
                .getJSONObject("dataValue").toJavaObject(Business.class);

        t_user_business userBusiness = t_user_business.getInstance();

        if(StringUtils.isNotBlank(business.getName())){
            userBusiness.setName(business.getName());
        }

        if(!business.getCompany().isEmpty()){
            userBusiness.setCompany(business.getCompany().toString());
        }

        if(!business.getDepartment().isEmpty()){
            userBusiness.setDepartment(business.getDepartment().get(0));
        }

        if(!business.getTitle().isEmpty()){
            userBusiness.setTitle(business.getTitle().get(0));
        }

        if(!business.getTel_cell().isEmpty()){
            userBusiness.setTelCell(business.getTel_cell().get(0));
        }

        if(!business.getTel_work().isEmpty()){
            userBusiness.setTelWork(business.getTel_work().get(0));
        }

        if(!business.getEmail().isEmpty()){
            userBusiness.setEmail(business.getEmail().get(0));
        }

        if(!business.getAddr().isEmpty()){
            userBusiness.setAddress(business.getAddr().get(0));
        }

        return userBusiness;
    }

    @Transactional
    public Message<t_user_business> addBusiness(t_user_business userBusiness,long userId){
        t_user user = new t_user();
        user.setId(userId);

        userBusiness.setUser(user);
        userBusiness.setAuditStatus(AuditStatus.SUPPLEMENT);//补充资料

        return Message.success(userBusinessDao.save(userBusiness));
    }

    @Transactional
    public Message<String> supplementUserBusiness(InvestPrefer investPrefer, String aboutText, String workText,long userId){

        if (investPrefer == InvestPrefer.NONE) {
            return Message.error("请选择投资偏好");
        }

        if (StringUtils.isBlank(aboutText)) {
            return Message.error("请填写个人介绍");
        }

        if (StringUtils.isBlank(workText)) {
            return Message.error("请填写工作经历");
        }

        t_user_business userBusiness =super.getOne(Qt_user_business.t_user_business.user.id.eq(userId));
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

        userBusinessDao.save(userBusiness);

        return Message.ok("提交成功,等待审核中");
    }

    public Message<UserBusiness> findUserBusinessDetail(long userId){
        Qt_user_business qtUserBusiness = Qt_user_business.t_user_business;

        Expression<UserBusiness> expression = Projections.constructor(UserBusiness.class
                , qtUserBusiness.name
                , qtUserBusiness.company
                , qtUserBusiness.department
                , qtUserBusiness.title
                , qtUserBusiness.telCell
                , qtUserBusiness.telWork
                , qtUserBusiness.address
                , qtUserBusiness.email
                , qtUserBusiness.businessCardUri
                , qtUserBusiness.investPrefer
                , qtUserBusiness.aboutText
                , qtUserBusiness.workText
                , qtUserBusiness.auditStatus
                , qtUserBusiness.verifyTime
        );
        Predicate predicate = qtUserBusiness.user.id.eq(userId);

        UserBusiness userBusiness = super.getOne(expression, predicate);

        if (userBusiness == null) {
            return Message.error("请上传名片");
        }

        if (StringUtils.equalsIgnoreCase(userBusiness.getAuditStatus(), AuditStatus.SUPPLEMENT.name())) {
            return Message.error("请补充名片资料");
        }

        return Message.success(userBusiness);
    }

}
