package com.hema.newretail.backstage.service.menu;

import com.hema.newretail.backstage.common.queryparam.menuclassify.MenuClassifyCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BusiMenuClassify;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.menu
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-24 15:30
 */
public interface MenuClassifyService {
    /**
     * 根据查询条件查询饮品分类列表
     *
     * @param vo 查询条件对象
     * @return 返回对象
     */
    Response queryList(MenuClassifyCondition vo);

    /**
     * 添加分类
     *
     * @param vo 对象
     * @return response
     */
    Response insert(BusiMenuClassify vo);

    /**
     * 更新分类
     *
     * @param vo 对象
     * @return response
     */
    Response update(BusiMenuClassify vo);

    /**
     * 删除分类
     *
     * @param vo 对象
     * @return response
     */
    Response delete(BusiMenuClassify vo);
}
