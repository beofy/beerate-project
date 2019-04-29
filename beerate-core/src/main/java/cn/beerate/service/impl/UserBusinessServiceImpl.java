package cn.beerate.service.impl;

import cn.beerate.model.bean.Business;
import cn.beerate.utils.BcrUtil;
import cn.beerate.common.Message;
import cn.beerate.dao.UserBusinessDao;
import cn.beerate.model.entity.t_user_business;
import cn.beerate.service.UserBusinessService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
@Transactional(readOnly = true)
public class UserBusinessServiceImpl extends BaseServiceImpl<t_user_business> implements UserBusinessService {

    private UserBusinessDao userBusinessDao;
    public UserBusinessServiceImpl(UserBusinessDao userBusinessDao) {
        super(userBusinessDao);
        this.userBusinessDao = userBusinessDao;
    }

    @Override
    public Message<String> uploadUserBusiness(String tempFilePath, String userFilePath,long userId) {
        File tempFile = new File(tempFilePath);

        String result = BcrUtil.parse(tempFile);
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

        if(StringUtils.isNotBlank(business.getCompany().get(0))){
            userBusiness.setCompany(business.getCompany().get(0));
        }

        if(StringUtils.isNotBlank(business.getDepartment().get(0))){
            userBusiness.setDepartment(business.getDepartment().get(0));
        }

        if(StringUtils.isNotBlank(business.getTitle().get(0))){
            userBusiness.setTitle(business.getTitle().get(0));
        }

        if(StringUtils.isNotBlank(business.getTel_cell().get(0))){
            userBusiness.setTelCell(business.getTel_cell().get(0));
        }

        if(StringUtils.isNotBlank(business.getTel_work().get(0))){
            userBusiness.setTelWork(business.getTel_work().get(0));
        }

        if(StringUtils.isNotBlank(business.getEmail().get(0))){
            userBusiness.setEmail(business.getEmail().get(0));
        }

        if(StringUtils.isNotBlank(business.getAddr().get(0))){
            userBusiness.setAddress(business.getAddr().get(0));
        }

        //设置用户id
        userBusiness.getUser().setId(userId);
        //保存名片文件

        return null;
    }
}
