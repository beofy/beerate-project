package cn.beerate.dao;

import cn.beerate.model.ItemModel;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ItemCommonDao<T extends ItemModel> extends IBaseDao<T>{


}
