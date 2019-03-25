package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BusiIngredientMenuEntry;
import com.hema.newretail.backstage.model.common.AgentCompanyBo;

import java.util.List;
import java.util.Map;

public interface BusiIngredientMenuMapper {
    List<AgentCompanyBo> selectAll();

    List<BusiIngredientMenuEntry> selectAllNoDiy();

    int deleteByPrimaryKey(Long id);

    int insert(BusiIngredientMenuEntry record);

    int insertSelective(BusiIngredientMenuEntry record);

    BusiIngredientMenuEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiIngredientMenuEntry record);

    int updateByPrimaryKey(BusiIngredientMenuEntry record);

    void saveStandardDrink(BusiIngredientMenuEntry busiIngredientMenuEntry);

    BusiIngredientMenuEntry selectStandardDrink(String menuName);
}