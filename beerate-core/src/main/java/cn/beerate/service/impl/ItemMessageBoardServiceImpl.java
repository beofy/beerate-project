package cn.beerate.service.impl;

import cn.beerate.dao.ItemMessageBoardDao;
import cn.beerate.model.entity.t_item_message_board;
import cn.beerate.service.ItemMessageBoardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ItemMessageBoardServiceImpl extends BaseServiceImpl<t_item_message_board> implements ItemMessageBoardService {
    private ItemMessageBoardDao itemMessageBoardDao;
    public ItemMessageBoardServiceImpl(ItemMessageBoardDao itemMessageBoardDao) {
        super(itemMessageBoardDao);
        this.itemMessageBoardDao = itemMessageBoardDao;
    }

}
