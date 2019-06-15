package cn.beerate.dao;

//import cn.beerate.dao.support.SqlQueryExecutor;
//import cn.beerate.model.dto.BlockTrade;
//import cn.beerate.model.dto.BlockTradeInterFace;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.Predicate;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan("cn.beerate.model.entity")
public class PageTest {

//    @Autowired
//    private BlockTradeDao blockTradeDao;
//
//    @Autowired
//    private SqlQueryExecutor sqlQueryExecutor;
//
//    @Autowired
//    protected ObjectMapper objectMapper;
//
//    @Test
//    public void testPageOfBlockTrade1() {
//        List<Map> blockTrades = blockTradeDao.pageOfBlockTrade1();
//        blockTrades.forEach(map -> {
//            try {
//                System.out.println(objectMapper.writeValueAsString(map));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @Test
//    public void testPageOfBlockTrade2() {
//        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.fromString("asc"), "id"));
//        Page<BlockTradeInterFace> blockTrades = blockTradeDao.pageOfBlockTrade2(pageable);
//        blockTrades.getContent().forEach(map -> {
//            try {
//                System.out.println(objectMapper.writeValueAsString(map));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @Test
//    public void testPageOfBlockTrade3() {
//        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.fromString("asc"), "id"));
//
//
//
//        Page<BlockTradeInterFace> blockTrades = blockTradeDao.pageOfBlockTrade3(null, pageable);
//
//        blockTrades.getContent().forEach(map -> {
//            try {
//                System.out.println(objectMapper.writeValueAsString(map));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        });
//
//    }
//
//    @Test
//    public void testPage3() {
////       BlockTrade blockTrade = blockTradeDao.findByBlockTradeName("阿斯顿");
////       System.out.println(blockTrade);
//
////        BlockTrade blockTrade = blockTradeDao.findByAdmin_Id(1L);
////        System.out.println(blockTrade);
//
//
////        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.fromString("desc"), "id"));
////        Page<BlockTrade> blockTrade = blockTradeDao.findByAdmin_Id(1L,pageable);
////
////        blockTrade.getContent().forEach(blockTrade1 -> {
////            System.out.println(blockTrade1);
////        });
//
//        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.fromString("asc"), "id"));
//        Page<BlockTrade> blockTrade = blockTradeDao.findBy(pageable);
//        blockTrade.getContent().forEach(blockTrade1 -> {
//            System.out.println(blockTrade1);
//        });
//
//
//
////        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.fromString("asc"), "id"));
////        final String field="blockTradeName";
////        final String value="阿斯顿";
////        final Date beginDate= DateUtil.parse("2019-05-06 14:38:06");
////        final Date endDate=DateUtil.parse("2019-05-08 14:38:06");
////
////        Page<BlockTrade> blockTrade = blockTradeDao.findBy(pageable,(root, query, criteriaBuilder) -> {
////            List<Predicate> predicates = new ArrayList<>();
////            if(beginDate!=null&&endDate!=null&&beginDate.before(endDate)){
////                predicates.add(criteriaBuilder.between(root.get("createTime") , beginDate, endDate));
////            }
////
////            if(StringUtils.isNotBlank(field)){
////                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get(field),"%"+value+"%")));
////            }
////
////            Predicate[] predicate = new Predicate[predicates.size()];
////            return criteriaBuilder.and(predicates.toArray(predicate));
////        });
////
////        blockTrade.getContent().forEach(blockTrade1 -> {
////            System.out.println(blockTrade1);
////        });
//
//    }
//
//
//
//
//
//    @Test
//    public void testSqlQuery(){
//        System.out.println(sqlQueryExecutor);
//    }


}
