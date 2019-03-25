package com.hema.newretail.backstage.controller;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.BaseIngredientInfoVerifyCodeParameter;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BaseIngredientInfoEntry;
import com.hema.newretail.backstage.service.IBaseIngredientInfoDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
@Api(description = "配料信息字典")
@RestController
@RequestMapping(value = "/baseIngredientInfo")
@AutoLog
public class BaseIngredientInfoController {

    private static final String DELETESUCCESS = "删除成功";

    @Autowired
    private IBaseIngredientInfoDataService baseIngredientInfoService;

    /**
     *配料管理列表接口
     * @param params {pageNum:int(必填),pageSize:int(必填),keyword:String(可选)}
     * @return
     */
    @ApiOperation(value = "查询所有配料信息")
    @PostMapping("/findAll")
    @ResponseBody
    public Response findAll(@RequestBody Map<String,Object> params){
        return baseIngredientInfoService.findAll(params);
    }

    /**
     *配料管理详情接口
     * @param id
     * @return
     */
    @ApiOperation(value = "查询一条配料信息")
    @PostMapping("/findOneById")
    public Response findOneById(@RequestBody long id){
        return Response.success(baseIngredientInfoService.findOneById(id));
    }

    /**
     *配料保存接口
     * @param data
     * @return
     */
    @ApiOperation(value = "添加/编辑配料")
    @PostMapping("/saveBaseIngredientInfo")
    public Response saveBaseIngredientInfo(@Validated @RequestBody BaseIngredientInfoEntry data, BindingResult result){
        if(result.hasErrors()){
            return Response.failure(result.getFieldError().getDefaultMessage());
        }else {
            if(baseIngredientInfoService.save(data)){
                return Response.success();
            }else{
                return Response.failure("保存失败！");
            }
        }
    }

    /**
     * 配料删除接口
     * @param id
     * @return
     */
    @ApiOperation(value = "删除配料")
    @PostMapping("/deleteBaseIngredientInfo")
    public Response deleteBaseIngredientInfo(long id){
        String result = baseIngredientInfoService.deleteById(id);
        if (DELETESUCCESS.equals(result)){
            return Response.success();
        }else{
            return Response.failure(result);
        }
    }

    @ApiOperation(value = "验证配料编码是否重复")
    @PostMapping("/verifyIngredientCode")
    public Response verifyIngredientCode(@RequestBody BaseIngredientInfoVerifyCodeParameter code){
        if(code.getCode()!=null){
            int count = baseIngredientInfoService.verifyIngredientCode(code.getCode());
            if(count>0){
                return Response.failure("符号标识重复，请重新输入");
            }else {
                return Response.success();
            }
        }else {
            return Response.success("code为null");
        }

    }
}
