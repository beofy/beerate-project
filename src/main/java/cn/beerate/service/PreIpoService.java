package cn.beerate.service;

import cn.beerate.common.Message;
import cn.beerate.model.dto.*;
import cn.beerate.model.entity.t_item_block_trade;
import cn.beerate.model.entity.t_item_loan;
import cn.beerate.model.entity.t_item_pre_ipo;
import org.springframework.data.domain.Page;

public interface PreIpoService extends ItemCommonService<t_item_pre_ipo> {

    /**
     * 查询我的融资列表
     */
    Page<MyPreIpo> pageMyPreIpoUser(int page, int size, String column, String order, long userId);

    /**
     * 查询项目详情
     */
    PreIpoDetail preIpoDetailByUser(long preIpoId, long userId);

    /**
     * 更新融资项目信息
     */
    Message<t_item_pre_ipo> updateItemByUser(t_item_pre_ipo preIpo, long itemId, long userId) ;

    /**
     * 项目集列表
     */
    Page<PreIpo> pagePreIpo(int page, int size, String column, String order);

}
