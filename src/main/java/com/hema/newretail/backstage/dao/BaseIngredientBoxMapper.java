package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseIngredientBoxEntry;
import com.hema.newretail.backstage.model.tag.BaseIngredientBoxInfoBo;
import com.hema.newretail.backstage.model.taskkafka.IngredientBoxBo;

import java.util.List;

public interface BaseIngredientBoxMapper {

    List<BaseIngredientBoxInfoBo> selectBoxOrInfoByBoxGroupId(Long boxGroupId);

    int deleteByPrimaryKey(Long id);

    int deleteByGroupId(Long id);

    int insert(BaseIngredientBoxEntry record);

    int insertSelective(BaseIngredientBoxEntry record);

    BaseIngredientBoxEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseIngredientBoxEntry record);

    int updateByPrimaryKey(BaseIngredientBoxEntry record);

    /**
     * 查询配料方案的料盒信息给task用
     *
     * @param boxGroupId 配料方案ID
     * @return list
     */
    List<IngredientBoxBo> selectByBoxGroupIdForTask(Long boxGroupId);
}