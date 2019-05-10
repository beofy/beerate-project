package cn.beerate.service.impl;

import cn.beerate.model.AuditStatus;
import cn.beerate.model.dto.Business;
import cn.beerate.model.entity.t_user;
import cn.beerate.utils.BcrUtil;
import cn.beerate.common.Message;
import cn.beerate.dao.UserBusinessDao;
import cn.beerate.model.entity.t_user_business;
import cn.beerate.service.UserBusinessService;
import com.alibaba.fastjson.JSONObject;
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


}
