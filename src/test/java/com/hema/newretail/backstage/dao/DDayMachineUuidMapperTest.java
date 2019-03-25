package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.system.UpdateByMachinePicCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Department 新零售
 * @ClassName DDayMachineUuidMapperTest
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/12/17 19:17
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DDayMachineUuidMapperTest {

    @Autowired
    private ZoneBaseMapper zoneBaseMapper;

    @Test
    public void selectBaseResultMap() {
        UpdateByMachinePicCondition condition = new UpdateByMachinePicCondition();
        List<Long> l = new ArrayList<>();
        l.add(1l);
        l.add(2l);
        l.add(3l);
        condition.setId(l);
        condition.setType(null);
        condition.setMId(0l);
        zoneBaseMapper.updateByMachinePic(condition);
    }
}