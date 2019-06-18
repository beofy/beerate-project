package cn.beerate.dao;

import cn.beerate.model.ItemModel;
import cn.beerate.model.entity.t_item_loan;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ItemCommonDao<T extends ItemModel> extends IBaseDao<T>{

    T findByIdAndUserId(long itemId, long userId);

}
