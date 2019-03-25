package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.common.GlobalCondition;
import com.hema.newretail.backstage.entry.IngredientMenuEntry;
import com.hema.newretail.backstage.model.menu.IngredientMenuBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
public interface IngredientMenuEntryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IngredientMenuEntry record);

    int insertSelective(IngredientMenuEntry record);

    IngredientMenuEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IngredientMenuEntry record);

    int updateByPrimaryKey(IngredientMenuEntry record);

    /**
     * @param params
     * @return
     */
    List<IngredientMenuBo> selectDataByCondition(@Param("params") Map<String, Object> params,
                                                 @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * @param ids
     * @return
     */
    int deleteBatch(Long[] ids);

    List<IngredientMenuEntry> getList();

    /**
     * 修改饮品价格
     *
     * @param record
     * @return
     */
    int updatePriceByPrimaryKey(IngredientMenuEntry record);

    /**
     * 查询所有饮品，重新组装DIY，分页显示
     *
     * @return
     */
    List<Map<String, Object>> selectAllMenus();
}