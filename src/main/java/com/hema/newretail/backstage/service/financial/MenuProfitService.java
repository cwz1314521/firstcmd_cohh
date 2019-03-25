package com.hema.newretail.backstage.service.financial;

import com.hema.newretail.backstage.common.queryparam.common.GlobalCondition;
import com.hema.newretail.backstage.common.queryparam.financial.MenuProfitCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BusiIngredientMenuProfit;

import javax.servlet.http.HttpSession;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.financial.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-07 15:04
 */
public interface MenuProfitService {
    /**
     * 根据查询条件查询饮品分润规则
     *
     * @param vo
     * @return
     */
    Response queryList(MenuProfitCondition vo);

    /**
     * 插入一条记录
     *
     * @param vo
     * @param session
     * @return
     */
    Response insert(BusiIngredientMenuProfit vo, HttpSession session);

    /**
     * 修改一条记录
     *
     * @param vo
     * @param session
     * @return
     */
    Response update(BusiIngredientMenuProfit vo, HttpSession session);

    /**
     * 启用/停用分润规则
     *
     * @param vo
     * @return
     */
    Response stop(BusiIngredientMenuProfit vo);

    /**
     * 查询所有饮品，重新组装DIY，分页显示
     *
     * @return
     */
    Response menus();
//    Response menus(GlobalCondition vo);
}
