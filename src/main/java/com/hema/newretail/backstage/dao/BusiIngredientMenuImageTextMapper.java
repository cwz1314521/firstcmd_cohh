package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BusiIngredientMenuImageText;

public interface BusiIngredientMenuImageTextMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusiIngredientMenuImageText record);

    int insertSelective(BusiIngredientMenuImageText record);

    BusiIngredientMenuImageText selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiIngredientMenuImageText record);

    int updateByPrimaryKeyWithBLOBs(BusiIngredientMenuImageText record);

    int updateByPrimaryKey(BusiIngredientMenuImageText record);
}