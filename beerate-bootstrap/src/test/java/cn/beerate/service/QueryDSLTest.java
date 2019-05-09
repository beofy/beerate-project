package cn.beerate.service;

import cn.beerate.dao.ICustomQueryDsl;
import cn.beerate.model.bean.BlockTrade;
import cn.beerate.model.entity.Qt_admin;
import cn.beerate.model.entity.Qt_item_block_trade;
import cn.beerate.model.entity.t_admin;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryDSLTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ICustomQueryDsl iCustomQueryDsl;

    @Test
    @Transactional
    public void testPage() {
        Querydsl querydsl = new Querydsl(entityManager,new PathBuilder<>(Qt_admin.t_admin.getType(),Qt_admin.t_admin.getMetadata()));

        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString("desc"),"id");
        //分页排序
        Pageable pageable = PageRequest.of(0,1,sort);

        JPQLQuery<t_admin> jpaQuery = querydsl.createQuery();

        querydsl.applyPagination(pageable,jpaQuery).from(Qt_admin.t_admin);

        Page<t_admin> page = PageableExecutionUtils.getPage(jpaQuery.fetch(),pageable, jpaQuery::fetchCount);

        //Page<t_admin> page = adminService.page(1, 1, "id", "desc", Qt_admin.t_admin.id.eq(1L));
        System.out.println(page.getContent().get(0).getBlock_trade());
    }



    @Test
    public void testCustomQueryDsl(){
        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString("desc"),"id");

        //分页排序
        Pageable pageable = PageRequest.of(0,1,sort);

        Qt_item_block_trade blockTrade =Qt_item_block_trade.t_item_block_trade;

        //查询字段
        Expression<BlockTrade> expression =Projections.constructor(BlockTrade.class,blockTrade.blockTradeName,blockTrade.stockCode);

        //查询条件
        Predicate predicate = blockTrade.id.eq(1L);

        Page<BlockTrade> page= iCustomQueryDsl.findAll(expression,blockTrade,predicate,pageable);

        System.out.println(page);

    }



    @Test
    public void testEm(){

//        List  list = entityManager.createNativeQuery("SELECT\n" +
//                "\tt_item_blo0_.blockTradeName AS `blockTradeName`,\n" +
//                "\tt_item_blo0_.stockCode AS `stockCode`\n" +
//                "FROM\n" +
//                "\tt_item_block_trade t_item_blo0_", BlockTrade.class).getResultList();
//
//        System.out.println(list);

    }


    @Test
    public void testDsl() {

//        //字段查询
//        Qt_admin qt_admin=Qt_admin.t_admin;
//        List<t_admin> list = jpaQueryFactory.selectFrom(qt_admin).fetch();
//        System.out.println(list);
//
//
//        //字段查询
//        Qt_admin qt_admin=Qt_admin.t_admin;
//        List<t_admin> list = jpaQueryFactory.select(qt_admin).from(qt_admin).fetch();
//        System.out.println(list);
//
//
//        List list = jpaQueryFactory
//                .select(Qt_item_block_trade.t_item_block_trade.blockTradeName)
//                .from(Qt_item_block_trade.t_item_block_trade)
//                .where(Qt_item_block_trade.t_item_block_trade.admin.id.eq(1L))
//                .fetch();
//        System.out.println(list);


//        //非自定义bean，查询部分字段
//        List<Tuple> list = jpaQueryFactory
//                .select(Qt_item_block_trade.t_item_block_trade.id,Qt_item_block_trade.t_item_block_trade.blockTradeName)
//                .from(Qt_item_block_trade.t_item_block_trade)
//                .where(Qt_item_block_trade.t_item_block_trade.admin.id.eq(1L))
//                .fetch();
//
//        for (Tuple row : list) {
//            System.out.println("id " + row.get(Qt_item_block_trade.t_item_block_trade.id));
//            System.out.println("blockTradeName " + row.get(Qt_item_block_trade.t_item_block_trade.blockTradeName));
//
//        }
//        System.out.println(list);

        //自定义bean查询
        List<BlockTrade> list = new JPAQueryFactory(entityManager)
                .select(Projections.constructor(BlockTrade.class,Qt_item_block_trade.t_item_block_trade.blockTradeName,Qt_item_block_trade.t_item_block_trade.stockCode))
                .from(Qt_item_block_trade.t_item_block_trade)
                .fetch();

        System.out.println(list);


        //自定义分页查询
        //1.
//        JPQLQuery<t_admin> jpaQuery = jpqlQueryFactory.select(Qt_admin.t_admin).from(Qt_admin.t_admin).where();
//        jpaQuery.fetchCount();
//        jpaQuery.offset(0).limit(5).orderBy(Qt_admin.t_admin.id.asc()).fetch();
//
//        //2.
//        QueryResults<t_admin>  results =jpqlQueryFactory
//                .select(Qt_admin.t_admin)
//                .from(Qt_admin.t_admin)
//                .orderBy(Qt_admin.t_admin.id.asc())
//                .offset(1)
//                .limit(2)
//                .where()
//                .fetchResults();
//
//        System.out.println(results);


    }

}
