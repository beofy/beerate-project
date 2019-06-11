package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.entity.t_item_pre_ipo;

public interface PreIpoService extends ItemCommonService<t_item_pre_ipo> {

    Message<t_item_pre_ipo> addPreIpo(t_item_pre_ipo preIpo);

    Message<t_item_pre_ipo> addPreIpoByUser(t_item_pre_ipo preIpo, long userId);

    Message<t_item_pre_ipo> addPreIpoByAdmin(t_item_pre_ipo preIpo, long adminId);
    
}
