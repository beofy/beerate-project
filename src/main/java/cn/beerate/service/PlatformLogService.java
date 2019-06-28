package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_platform_log;

public interface PlatformLogService extends IBaseService<t_platform_log> {

    Message<t_platform_log> addPlatformLog(String operator, String action, String params, String result, String executionTime, String ipAddr, String operatorType);
}
