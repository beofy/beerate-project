package cn.beerate.service.impl;

import cn.beerate.dao.ItemMessageBoardDao;
import cn.beerate.model.entity.t_item_message_board;
import cn.beerate.service.ItemMessageBoardService;

public class ItemMessageBoardServiceImpl extends BaseServiceImpl<t_item_message_board> implements ItemMessageBoardService {
    private ItemMessageBoardDao itemMessageBoardDao;
    public ItemMessageBoardServiceImpl(ItemMessageBoardDao itemMessageBoardDao) {
        super(itemMessageBoardDao);
        this.itemMessageBoardDao = itemMessageBoardDao;
    }

}
