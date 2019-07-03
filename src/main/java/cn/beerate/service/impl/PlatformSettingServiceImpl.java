package cn.beerate.service.impl;

import cn.beerate.dao.PlatformSettingDao;
import cn.beerate.model.entity.t_platform_setting;
import cn.beerate.service.PlatformSettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatformSettingServiceImpl extends BaseServiceImpl<t_platform_setting> implements PlatformSettingService {
    private PlatformSettingDao platformSettingDao;
    public PlatformSettingServiceImpl(PlatformSettingDao platformSettingDao) {
        super(platformSettingDao);
        this.platformSettingDao = platformSettingDao;
    }

    @Override
    @Transactional
    public t_platform_setting findBySetKey(String key){
        t_platform_setting setting = platformSettingDao.findBySetKey(key);
        if (setting==null){
            updateSetValueByKey(key,"");
        }

        return setting;
    }

    @Override
    @Transactional
    public t_platform_setting updateSetValueByKey(String key,String value){
        t_platform_setting setting = platformSettingDao.findBySetKey(key);

        //不存在时添加一条
        if (setting==null){
            t_platform_setting defaultSetting = new t_platform_setting();
            defaultSetting.setSetKey(key);
            defaultSetting.setSetValue(value);
            defaultSetting.setDescription("");
            return platformSettingDao.save(defaultSetting);
        }

        //更新参数
        setting.setSetValue(value);

        return platformSettingDao.save(setting);
    }

}
