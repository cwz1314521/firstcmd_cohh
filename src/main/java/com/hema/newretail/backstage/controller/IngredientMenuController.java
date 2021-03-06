package com.hema.newretail.backstage.controller;

import com.github.pagehelper.Page;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.model.menu.IngredientMenuBo;
import com.hema.newretail.backstage.service.IIngredientMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hema-newetaril-com.hema.newretail.backstage.controller
 *
 * @Description:
 * @Author: ZhangHaiSheng
 * @Date: 2018-08-23 11:14
 */
@Api("饮品管理")
@RestController
@Validated
@RequestMapping("/menu")
@AutoLog
public class IngredientMenuController {

    @Autowired
    private IIngredientMenuService ingredientMenuService;

    /**
     * 查询饮品，分页显示
     *
     * @param pageNum         页码
     * @param pageSize        每页数据条数
     * @param menuName        饮品名称
     * @param priceStart      饮品价格起始值
     * @param priceEnd        饮品价格结束值
     * @param isRecommend     是否推荐
     * @param status          展示状态
     * @return
     */
    @ApiOperation("查询饮品")
    @PostMapping(value = "/list")
    public Response list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                         @RequestParam(value = "menuName", required = false) String menuName,
                         @RequestParam(value = "priceStart", required = false) String priceStart,
                         @RequestParam(value = "priceEnd", required = false) String priceEnd,
                         @RequestParam(value = "isRecommend", required = false) String isRecommend,
                         @RequestParam(value = "status", required = false) String status,
                         @RequestParam(value = "menuClassifyId", required = false) Long menuClassifyId) {
        Map<String, Object> paramsMap = new HashMap<>(11);
        paramsMap.put("menuName", menuName);
        paramsMap.put("priceStart", StringUtils.isEmpty(priceStart) ? null : new BigDecimal(priceStart));
        paramsMap.put("priceEnd", StringUtils.isEmpty(priceEnd) ? null : new BigDecimal(priceEnd));
        paramsMap.put("isRecommend", StringUtils.isEmpty(isRecommend) ? null : Integer.valueOf(isRecommend));
        paramsMap.put("status", StringUtils.isEmpty(status) ? null : Boolean.valueOf(status));
        paramsMap.put("pageNum", pageNum);
        paramsMap.put("pageSize", pageSize);
        paramsMap.put("menuClassifyId", menuClassifyId);
        try {
            List<IngredientMenuBo> list = ingredientMenuService.queryDataByConditions(paramsMap);
            return Response.success(list, ((Page) list).getTotal(), pageSize, pageNum);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    /**
     * 批量删除饮品
     *
     * @param menuIds 饮品编码数组
     * @return
     */
    @ApiOperation("批量删除饮品")
    @PostMapping(value = "deleteMulti")
    public Response deleteMulti(Long[] menuIds) {
        try {
            if (null == menuIds || menuIds.length < 1) {
                return Response.failure();
            }
            ingredientMenuService.deleteBatch(menuIds);
            return Response.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    /**
     * 跳转到添加页面需要的初始数据
     *
     * @return
     */
    @ApiOperation("查询添加页面需要的初始数据")
    @PostMapping(value = "toAdd")
    public Response toAdd() {
        try {
            return Response.success(ingredientMenuService.queryInitDataForInsertMenu());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    /**
     * 跳转到编辑页面需要的初始数据
     *
     * @param menuId 饮品编码
     * @return
     */
    @ApiOperation("查询编辑页面需要的初始数据")
    @PostMapping(value = "toUpdate")
    public Response toUpdate(@RequestParam Long menuId) {
        try {
            return Response.success(ingredientMenuService.queryMenuDetailByMenuId(menuId));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

}
