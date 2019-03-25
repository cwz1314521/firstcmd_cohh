package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.financial.MenuProfitCondition;
import com.hema.newretail.backstage.entry.BusiIngredientMenuProfit;
import com.hema.newretail.backstage.model.financial.MenuProfitBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusiIngredientMenuProfitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusiIngredientMenuProfit record);

    int insertSelective(BusiIngredientMenuProfit record);

    BusiIngredientMenuProfit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiIngredientMenuProfit record);

    int updateByPrimaryKey(BusiIngredientMenuProfit record);

    /**
     * 查询饮品分润
     *
     * @param vo
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<MenuProfitBo> selectList(@Param("vo") MenuProfitCondition vo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * 查询指定的规则是否过期
     *
     * @param vo
     * @return
     */
    Long selectNum(BusiIngredientMenuProfit vo);

    /**
     * 更新制定饮品为停用
     *
     * @param vo
     * @return
     */
    int updateIsDeleted(BusiIngredientMenuProfit vo);
}