package cn.beerate.service.impl;

import cn.beerate.common.Message;
import cn.beerate.dao.StockTransferDao;
import cn.beerate.model.AuditStatus;
import cn.beerate.model.IndustryRealm;
import cn.beerate.model.ItemType;
import cn.beerate.model.dto.MyStockTransfer;
import cn.beerate.model.dto.StockTransfer;
import cn.beerate.model.dto.StockTransferDetail;
import cn.beerate.model.entity.t_item_stock_transfer;
import cn.beerate.model.entity.t_user_item_delivery;
import cn.beerate.service.StockTransferService;
import cn.beerate.service.UserItemDeliveryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StockTransferServiceImpl extends ItemCommonServiceImpl<t_item_stock_transfer> implements StockTransferService {

    private StockTransferDao stockTransferDao;
    private UserItemDeliveryService userItemDeliveryService;
    public StockTransferServiceImpl(StockTransferDao stockTransferDao,UserItemDeliveryService userItemDeliveryService) {
        super(stockTransferDao);
        this.stockTransferDao = stockTransferDao;
        this.userItemDeliveryService=userItemDeliveryService;
    }

    private Message<String> stockTransferValid(t_item_stock_transfer stockTransfer) {

        if (StringUtils.isBlank(stockTransfer.getBidName())) {
            return Message.error("请填写标的名称");
        }

        if (stockTransfer.getIsQuoted() == null) {
            return Message.error("请选择标的所属阶段");
        }

        if (stockTransfer.getCompanyNameIsPublic() == null) {
            return Message.error("请选择企业名称是否公开");
        }

        if (StringUtils.isBlank(stockTransfer.getCompanyName())) {
            return Message.error("请填写企业名称");
        }

        if (stockTransfer.getIndustryRealm() == IndustryRealm.NONE) {
            return Message.error("请选择行业领域");
        }

        if (stockTransfer.getCurrency() == null) {
            return Message.error("请选择币种");
        }

        if (stockTransfer.getLastYearProfits() == null || stockTransfer.getLastYearProfits() < 0) {
            return Message.error("请填写去年净利润");
        }

        if (stockTransfer.getCurrentValuation() == null || stockTransfer.getCurrentValuation() < 0) {
            return Message.error("请填写本轮估值");
        }

        if (stockTransfer.getTransferAmount() == null || stockTransfer.getTransferAmount() < 0) {
            return Message.error("请填写转让金额");
        }

        if (stockTransfer.getIsPrivacyEquityRatio() == null) {
            return Message.error("请选择转让股权比例是否保密");
        }

        if (stockTransfer.getEquityRatio() == null || stockTransfer.getEquityRatio() < 0) {
            return Message.error("请填写转让股权比例");
        }

        if (StringUtils.isBlank(stockTransfer.getInvestLightSpot())) {
            return Message.error("请填写投资亮点");
        }

        if (StringUtils.isBlank(stockTransfer.getContact())) {
            return Message.error("请填写联系人");
        }

        if (StringUtils.isBlank(stockTransfer.getContactMobile())) {
            return Message.error("请填写联系人电话");
        }

        if (StringUtils.isBlank(stockTransfer.getContentDescription())) {
            return Message.error("请填写内容描述");
        }

        return Message.ok("检验通过");
    }

    @Override
    @Transactional
    public Message<t_item_stock_transfer> addItem(t_item_stock_transfer stockTransfer) {
        Message<String> message = stockTransferValid(stockTransfer);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        return super.addItem(stockTransfer);
    }

    @Override
    @Transactional
    public Message<t_item_stock_transfer> addItemByUser(t_item_stock_transfer stockTransfer, long userId) {
        Message<t_item_stock_transfer> message1 = super.addItemByUser(stockTransfer, userId);
        if (message1.fail()){
            return message1;
        }

        //添加投递项目列表
        t_item_stock_transfer stock_transfer = message1.getData();
        Message<t_user_item_delivery> message2 = userItemDeliveryService.addUserItemDelivery(stock_transfer.getUser().getId(),stock_transfer.getId(),stock_transfer.getBidName(), ItemType.STOCK_TRANSFER);
        if (message2.fail()){
            return Message.error(message2.getMsg());
        }

        return message1;
    }

    @Override
    public Page<MyStockTransfer> pageMyStockTransfer(int page, int size, String column, String order, long userId) {
        return stockTransferDao.pageMyStockTransferByUser(getPageable(page, size, column, order),userId);
    }

    @Override
    public StockTransferDetail stockTransferDetailByUser(long stockTransferId, long userId) {
        return stockTransferDao.stockTransferDetailByUser(stockTransferId,userId);
    }

    @Override
    @Transactional
    public Message<t_item_stock_transfer> updateItemByUser(t_item_stock_transfer stockTransfer, long itemId, long userId) {
        //参数校验
        Message message = stockTransferValid(stockTransfer);
        if (message.fail()) {
            return Message.error(message.getMsg());
        }

        t_item_stock_transfer stock_transfer = stockTransferDao.findByIdAndUserId(itemId, userId);
        if (stock_transfer.getAuditStatus() != AuditStatus.SUPPLEMENT) {
            return Message.error("非补充资料状态");
        }

        //更改信息
        stock_transfer.setBidName(stockTransfer.getBidName());
        stock_transfer.setIsQuoted(stockTransfer.getIsQuoted());
        stock_transfer.setCompanyNameIsPublic(stockTransfer.getCompanyNameIsPublic());
        stock_transfer.setCompanyName(stockTransfer.getCompanyName());
        stock_transfer.setIndustryRealm(stockTransfer.getIndustryRealm());
        stock_transfer.setCurrency(stockTransfer.getCurrency());
        stock_transfer.setLastYearProfits(stockTransfer.getLastYearProfits());
        stock_transfer.setCurrentValuation(stockTransfer.getCurrentValuation());
        stock_transfer.setTransferAmount(stockTransfer.getTransferAmount());
        stock_transfer.setIsPrivacyEquityRatio(stockTransfer.getIsPrivacyEquityRatio());
        stock_transfer.setEquityRatio(stockTransfer.getEquityRatio());
        stock_transfer.setInvestLightSpot(stockTransfer.getInvestLightSpot());
        stock_transfer.setContact(stockTransfer.getContact());
        stock_transfer.setContactMobile(stockTransfer.getContactMobile());
        stock_transfer.setContentDescription(stockTransfer.getContentDescription());

        return super.updateItem(stock_transfer);
    }

    @Override
    public Page<StockTransfer> pageStockTransfer(int page, int size, String column, String order) {
        return stockTransferDao.pageStockTransfer(getPageable(page, size, column, order));
    }
}
