package cn.beerate.dao;

import cn.beerate.dao.impl.LoanRepository;
import cn.beerate.model.entity.t_item_loan;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDao extends ItemCommonDao<t_item_loan>, LoanRepository {

    t_item_loan findByIdAndUserId(long itemId,long userId);
}
