package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.model.organization.BaseOrganizationBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Department 新零售
 * @ClassName BaseOrganizationMapperTest
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/16 10:33
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseOrganizationMapperTest {
    @Autowired
    private BaseOrganizationMapper mapper;
    @Test
    public void selectListBo() {
//        Map map = new HashMap(2);
//        map.put("orgCode","000");
//        map.put("level",0);
//        List<BaseOrganizationBo> baseOrganizationBos = mapper.SelectListBo(map);
//        System.out.println(baseOrganizationBos);
    }
}