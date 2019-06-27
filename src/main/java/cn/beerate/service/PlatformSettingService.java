package cn.beerate.service;

import cn.beerate.model.entity.t_platform_setting;

public interface PlatformSettingService extends IBaseService<t_platform_setting>{

    /**
     * 根据参数键查找
     */
    t_platform_setting findBySetKey(String key);

    /**
     * 根据参数键跟新
     */
    t_platform_setting updateSetValueByKey(String key,String value);
}
