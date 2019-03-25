package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.discounts.EventRuleListCondition;
import com.hema.newretail.backstage.common.queryparam.discounts.MenuOrMachineCondition;
import com.hema.newretail.backstage.entry.BusiActivityRuleEntry;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface BusiActivityRuleMapper {



    List<Map> selectMachineName(MenuOrMachineCondition condition);

    List<Map> selectMenuName(MenuOrMachineCondition condition);

    int selectWithActive(Long id);

    int deleteByRuleId(Long id);

    int selectResetName(Map map);

    List<BusiActivityRuleEntry> list(EventRuleListCondition condition);

    int deleteByPrimaryKey(Long id);

    int insert(BusiActivityRuleEntry record);

    int insertSelective(BusiActivityRuleEntry record);

    BusiActivityRuleEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiActivityRuleEntry record);

    int updateByPrimaryKey(BusiActivityRuleEntry record);

    List<Map<String,Object>> selectAllActivityRule();
}