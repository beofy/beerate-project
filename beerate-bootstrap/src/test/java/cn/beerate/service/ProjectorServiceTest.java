package cn.beerate.service;

import cn.beerate.model.InvestPrefer;
import cn.beerate.model.dto.ItemDeliveryList;
import cn.beerate.model.dto.ProjectorList;
import cn.beerate.model.entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectorServiceTest {

    @Autowired
    private ProjectorService projectorService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testPageOfProjectList() {
        projectorService.pageOfProjectList(1, 10, "id", "desc");


    }

    @Test
    public void testJoin() {
        Qt_user_business userBusiness = Qt_user_business.t_user_business;

        Querydsl querydsl = new Querydsl(entityManager, new PathBuilder<t_user_business>(Qt_user_business.t_user_business.getType(), Qt_user_business.t_user_business.getMetadata()));

        JPQLQuery<Tuple> jpaQuery = querydsl.createQuery().select(
                userBusiness.user.id,
                userBusiness.name,
                userBusiness.company,
                userBusiness.title,
                JPAExpressions.select(Qt_user_visitor.t_user_visitor.count()).from(Qt_user_visitor.t_user_visitor).where(Qt_user_visitor.t_user_visitor.visitor_user.id.eq(userBusiness.user.id)),
                JPAExpressions.select(Qt_item_delivery.t_item_delivery.count()).from(Qt_item_delivery.t_item_delivery).where(Qt_item_delivery.t_item_delivery.delivery_user.id.eq(userBusiness.user.id))
        );

        //排序条件
        Sort sort = Sort.by(Sort.Direction.fromString("desc"), "id");
        //分页排序
        Pageable pageable = PageRequest.of(0, 1, sort);

        querydsl.applyPagination(pageable, jpaQuery).from(userBusiness);
        jpaQuery.fetch();

        //PageableExecutionUtils.getPage(jpaQuery.fetch(), pageable, jpaQuery::fetchCount);
    }


}
