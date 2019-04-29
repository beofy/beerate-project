package cn.beerate.service.impl;

import cn.beerate.utils.BusinessParseUtil;
import cn.beerate.common.Message;
import cn.beerate.dao.UserBusinessDao;
import cn.beerate.model.entity.t_user_business;
import cn.beerate.service.UserBusinessService;
import com.alibaba.fastjson.JSONObject;
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
    public Message<String> uploadUserBusiness(String tempFilePath, String userFilePath) {
        File tempFile = new File(tempFilePath);
        String result = BusinessParseUtil.parse(tempFile);
        JSONObject
                .parseObject(result)
                .getJSONArray("outputs")
                .getJSONObject(0)
                .getJSONObject("outputValue")
                .getJSONObject("dataValue")

        ;

        return null;
    }
}
