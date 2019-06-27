package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.AdminRightDao;
import cn.beerate.dao.RightDao;
import cn.beerate.model.dto.AdminRight;
import cn.beerate.model.entity.t_admin_right;
import cn.beerate.model.entity.t_right;
import cn.beerate.service.AdminRightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminRightServiceImpl  implements AdminRightService {

    private RightDao rightDao;
    private AdminRightDao adminRightDao;

    private static Map<Long,t_right> rights=null;

    public AdminRightServiceImpl(RightDao rightDao, AdminRightDao adminRightDao) {
        this.rightDao = rightDao;
        this.adminRightDao = adminRightDao;
    }

    public Map<Long,t_right> getAdminRights(long adminId){
        Map<Long,t_right> rightMap = new HashMap<>();
        for (t_admin_right adminRight : adminRightDao.findByAdminId(adminId)) {
            long rightId = adminRight.getRightId();
            rightMap.put(rightId,getRights().get(rightId));
        }

        return rightMap;
    }

    public Map<Long,t_right> getRights(){
        if (rights!=null){
            return rights;
        }

        Map<Long,t_right> map = new HashMap<>();
        for (t_right right : rightDao.findAll()) {
            map.put(right.getId(),right);
        }

        rights=map;

        return map;
    }

    @Override
    @Transactional
    public Message<String> editAdminRight(AdminRight adminRight){
        long adminId = adminRight.getAdminId();
        adminRightDao.deleteByAdminId(adminRight.getAdminId());

        List<t_admin_right> adminRights= new ArrayList<>();
        for (Long rightId :adminRight.getRightIds()){
            t_admin_right right = new t_admin_right();
            right.setAdminId(adminId);
            right.setRightId(rightId);

            adminRights.add(right);
        }
        adminRightDao.saveAll(adminRights);

        return Message.ok("权限编辑成功");
    }

}
