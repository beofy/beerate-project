package cn.beerate.dao;

import cn.beerate.dao.impl.PreIpoRepository;
import cn.beerate.model.entity.t_item_pre_ipo;
import org.springframework.stereotype.Repository;

@Repository
public interface PreIpoDao extends ItemCommonDao<t_item_pre_ipo>, PreIpoRepository {
}
