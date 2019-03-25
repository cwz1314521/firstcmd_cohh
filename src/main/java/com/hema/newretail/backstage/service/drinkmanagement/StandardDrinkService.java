package com.hema.newretail.backstage.service.drinkmanagement;

import com.hema.newretail.backstage.common.queryparam.menu.BusiIngredientMenuImageTextCondition;
import com.hema.newretail.backstage.common.queryparam.menu.MenuImageTextCondition;
import com.hema.newretail.backstage.common.queryparam.menu.UpdateRecommendCondition;
import com.hema.newretail.backstage.common.requestparam.MenuParam;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BusiIngredientMenuEntry;
import com.hema.newretail.backstage.entry.BusiIngredientMenuImageText;

/**
 * Created by jiahao on 2018-08-25
 */
public interface StandardDrinkService {

    BusiIngredientMenuEntry selectStandardDrink(String menuName);

    String selectStandardDrink(String menuName, Long id);

    void saveAndUpdateStandardDrink(MenuParam menuParam);

    /**
     * 设置首页推荐
     *
     * @param menuParam
     * @return
     */
    Response updateRecommend(UpdateRecommendCondition menuParam);

    /**
     * 查看饮品图文
     *
     * @param condition
     * @return
     */
    Response imageTextDetail(MenuImageTextCondition condition);

    /**
     * 添加/编辑饮品图文
     *
     * @param condition
     * @return
     */
    Response updateMenuImageText(BusiIngredientMenuImageTextCondition condition);

}
