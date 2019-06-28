package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.PlatformLogDao;
import cn.beerate.model.entity.t_platform_log;
import cn.beerate.service.PlatformLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatformLogServiceImpl extends BaseServiceImpl<t_platform_log> implements PlatformLogService {
    private PlatformLogDao platformLogDao;

    public PlatformLogServiceImpl(PlatformLogDao platformLogDao) {
        super(platformLogDao);
        this.platformLogDao = platformLogDao;
    }

    @Override
    @Transactional
    public Message<t_platform_log> addPlatformLog(String operator, String action, String params, String result, String executionTime, String ipAddr, String operatorType) {
        t_platform_log platformLog = new t_platform_log();

        platformLog.setOperator(operator);
        platformLog.setAction(action);
        platformLog.setParams(params);
        platformLog.setResult(result);
        platformLog.setExecutionTime(executionTime);
        platformLog.setIpAddr(ipAddr);
        platformLog.setOperatorType(operatorType);

        if (platformLogDao.save(platformLog) == null) {
            return Message.error("添加系统日志失败");
        }

        return Message.success(platformLog);
    }

}
