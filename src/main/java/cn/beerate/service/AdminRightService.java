package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.AdminRight;
import cn.beerate.model.entity.t_right;

import java.util.Map;

public interface AdminRightService {

    /**
     * 获取管理员权限
     */
    Map<Long, t_right> getAdminRights(long adminId);

    /**
     * 获取所有的权限
     */
    Map<Long,t_right> getRights();

    /**
     * 编辑管理员权限
     */
    Message<String> editAdminRight(AdminRight adminRight);

}
