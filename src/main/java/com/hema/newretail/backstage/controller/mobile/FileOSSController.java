package com.hema.newretail.backstage.controller.mobile;

import com.hema.newretail.backstage.common.queryparam.mobile.IngredientNameByIdCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.mobile.FileOSSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Department 新零售
 * @ClassName FileOSSController
 * @Description 提供给文件中转操作
 * @Author ---CWZ
 * @Date 2018/11/16 10:16
 * @Version 1.0
 **/
@Api(description =  "提供给文件中转操作接口")
@Controller
@RequestMapping("/mobile")
public class FileOSSController {


    @Autowired
    private FileOSSService fileOSSService;

    /**
     *
     * 功能描述: 根据原料id查询原料名字 ---  加入redis缓存
     *
     * @param: IngredientNameByIdCondition
     * @return: Response
     * @author: cwz
     * @date: 2018/11/16 10:22
     */
    @ApiOperation("根据原料id查询原料名字")
    @PostMapping("/save")
    @ResponseBody
    public Response IngredientNameById(@RequestBody @Validated IngredientNameByIdCondition ingredientNameByIdCondition , BindingResult bindingResult){

        return fileOSSService.IngredientNameById(ingredientNameByIdCondition);
    }
    /**
     *
     * 功能描述: 文件转存
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/16 14:00
     */
}
