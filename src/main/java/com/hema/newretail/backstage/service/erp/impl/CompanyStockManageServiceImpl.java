package com.hema.newretail.backstage.service.erp.impl;

import com.hema.newretail.backstage.common.queryparam.erp.StockManageCondition;
import com.hema.newretail.backstage.dao.ErpOrderQrcodeMapper;
import com.hema.newretail.backstage.model.erp.StockManageBo;
import com.hema.newretail.backstage.service.erp.CompanyStockManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.erp.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-26 16:14
 */
@Service
public class CompanyStockManageServiceImpl implements CompanyStockManageService {

    @Autowired
    private ErpOrderQrcodeMapper erpOrderQrcodeMapper;

    @Override
    public List<StockManageBo> queryStockManageList(StockManageCondition vo) {
        return erpOrderQrcodeMapper.selectCompanyStockList(vo, vo.getPageNum(), vo.getPageSize());
    }

}
