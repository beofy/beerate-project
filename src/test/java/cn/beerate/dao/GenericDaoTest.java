package cn.beerate.dao;

import cn.beerate.model.dto.LoanList;
import cn.beerate.model.dto.UserBusiness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenericDaoTest {

    @Autowired
    private GenericDao genericDao;

    @Test
    @Transactional
    public void testPageable() {

        System.out.println("//=============================通用结果集查询==============================");
        String sql = "select * from t_user_business where id = :id";
        Map<String,Object> args = new HashMap<>();
        args.put("id",1);
        UserBusiness business = genericDao.getObject(sql, args, UserBusiness.class);
        System.out.println(business);





        System.out.println("//=============================统计条数==============================");

        String countSQL = "select count(1) from t_user_business where id = :id";
        Map<String,Object> args1 = new HashMap<>();
        args.put("id",1);
        Long count = genericDao.getCount(countSQL, args);
        System.out.println(count);



        System.out.println("//=============================测试集合查询==============================");
        String listSql = "SELECT id, itemName, industryRealm, amount, period , auditStatus FROM t_item_loan";
        List<LoanList> loanList = genericDao.getList(listSql,null,LoanList.class);
        System.out.println(loanList);





        System.out.println("//=============================测试分页查询==============================");
        String querySql = "SELECT id, itemName, industryRealm, amount, period , auditStatus FROM t_item_loan";
        String countSql = "SELECT count(1) FROM t_item_loan";

        Pageable pageable =PageRequest.of(0, 1, Sort.by(Sort.Direction.fromString("asc"), "id"));
        Page<LoanList> page = genericDao.getPage(querySql,countSql,null,pageable,LoanList.class);

        System.out.println(page);

    }

}
