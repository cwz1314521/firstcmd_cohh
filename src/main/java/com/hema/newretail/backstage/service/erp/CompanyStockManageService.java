package com.hema.newretail.backstage.service.erp;

import com.hema.newretail.backstage.common.queryparam.erp.StockManageCondition;
import com.hema.newretail.backstage.model.erp.StockManageBo;

import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.erp
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-26 16:14
 */
public interface CompanyStockManageService {
    /**
     * 查询分公司库存管理列表
     *
     * @param vo 参数
     * @return list
     */
    List<StockManageBo> queryStockManageList(StockManageCondition vo);
}
