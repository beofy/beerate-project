package cn.beerate.dao;

import cn.beerate.model.entity.t_platform_setting;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformSettingDao extends IBaseDao<t_platform_setting> {

    t_platform_setting findBySetKey(String key);
}
