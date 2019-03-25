package com.hema.newretail.backstage.controller.index;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.index.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.index.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @ClassName ArticleController
 * @Description 首页文章controller
 * @Author ---CWZ
 * @Date 2019/02/25 11:11
 * @Version 1.0
 **/

@Api(description = "≡(▔﹏▔)≡首页文章相关")
@Controller
@RequestMapping("/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private ArticleService articleService;

    /**
     *
     * 功能描述: 首页文章列表
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/2/25 15:13
     */
    @PostMapping(value = "/list")
    @ApiOperation("首页文章列表")
    @ResponseBody
    public Response list(@RequestBody @Validated ArticleListCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return articleService.list(condition);
        }
    }

    /**
     *
     * 功能描述: 首页文章添加
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/2/25 15:13
     */
    @PostMapping(value = "/add")
    @ApiOperation("首页文章添加")
    @ResponseBody
    public Response add(@RequestBody @Validated ArticleAddCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return articleService.add(condition);
        }
    }

    /**
     *
     * 功能描述: 首页文章修改
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/2/25 15:13
     */
    @PostMapping(value = "/edit")
    @ApiOperation("首页文章修改")
    @ResponseBody
    public Response edit(@RequestBody @Validated ArticleEditCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return articleService.edit(condition);
        }
    }

    /**
     *
     * 功能描述: 首页文章删除
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/2/25 15:13
     */
    @PostMapping(value = "/delete")
    @ApiOperation("首页文章修改")
    @ResponseBody
    public Response delete(@RequestBody @Validated ArticleDeleteCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return articleService.delete(condition);
        }
    }
}
