package com.hema.newretail.backstage.service.impl;

import com.hema.newretail.backstage.common.mongodb.BigDecimalToDecimal128Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName MakeOrderServiceImplTest
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/11/9 10:05
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MakeOrderServiceImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void gridCompany() {
//
//        big128 aaa = new big128();
//        final BigDecimal bigDecimal = aaa.getDd().bigDecimalValue();
//        aaa.setDd(new BigDecimalToDecimal128Converter().convert(new BigDecimal(12.00)));
//        mongoTemplate.save(aaa);
//        Query query = new Query();
//        query.addCriteria(Criteria.where("dd").gte(1).lte(3));//制作时间查询
//        List<big128> list = mongoTemplate.find(query, big128.class);
//        for (big128 b:list
//             ) {
//            System.out.println(b.getDd() + "#################################");
//        }
    }
}