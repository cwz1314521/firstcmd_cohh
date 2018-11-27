package com.hema.newretail.backstage.controller.erp;

import com.github.pagehelper.Page;
import com.hema.newretail.backstage.common.queryparam.erp.StockManageCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.model.erp.StockManageBo;
import com.hema.newretail.backstage.service.erp.CompanyStockManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller.erp
 * <p>
 * 分公司库存管理控制类
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-26 9:59
 */
@Api(description = "溯源系统->分公司库存管理")
@RestController
@RequestMapping("/stockManage")
public class CompanyStockManageController {

    @Autowired
    private CompanyStockManageService companyStockManageService;

    @ApiOperation("列表")
    @PostMapping("/list")
    public Response list(@RequestBody StockManageCondition vo) {
        try {
            List<StockManageBo> list = companyStockManageService.queryStockManageList(vo);
            return Response.success(list, ((Page) list).getTotal(), vo.getPageSize(), vo.getPageNum());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }
}
