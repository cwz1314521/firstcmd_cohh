package com.hema.newretail.backstage.controller.financial;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.common.GlobalCondition;
import com.hema.newretail.backstage.common.queryparam.financial.MenuProfitCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.validator.First;
import com.hema.newretail.backstage.common.validator.Second;
import com.hema.newretail.backstage.common.validator.Third;
import com.hema.newretail.backstage.entry.BusiIngredientMenuProfit;
import com.hema.newretail.backstage.service.financial.MenuProfitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller.financial
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-07 15:02
 */
@Api(description = "饮品分润设置")
@RestController
@RequestMapping("/financial/profit")
@AutoLog
public class MenuProfitController {

    @Autowired
    private MenuProfitService menuProfitService;

    @ApiOperation("查询所有的饮品分润")
    @PostMapping("/list")
    public Response list(@RequestBody MenuProfitCondition vo) {
        try {
            return menuProfitService.queryList(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    @ApiOperation("添加饮品分润")
    @PostMapping("/add")
    public Response add(@RequestBody @Validated({First.class}) BusiIngredientMenuProfit vo, HttpSession session) {
        try {
            return menuProfitService.insert(vo, session);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    @ApiOperation("编辑饮品分润")
    @PostMapping("/edit")
    public Response edit(@RequestBody @Validated({Second.class, First.class}) BusiIngredientMenuProfit vo, HttpSession session) {
        try {
            return menuProfitService.update(vo, session);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    @ApiOperation("停用/启用饮品分润")
    @PostMapping("/stop")
    public Response stop(@RequestBody @Validated({Second.class, Third.class}) BusiIngredientMenuProfit vo) {
        try {
            return menuProfitService.stop(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    @ApiOperation("查询所有饮品")
    @PostMapping("/menus")
    @ResponseBody
    public Response menus() {
        return menuProfitService.menus();
    }
}
