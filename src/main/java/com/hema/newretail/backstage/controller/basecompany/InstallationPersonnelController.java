package com.hema.newretail.backstage.controller.basecompany;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonAddCondition;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonDeleteCondition;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonEditCondition;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonListCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.basecompany.InstallationPersonnelService;
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

import javax.servlet.http.HttpServletRequest;

/**
 * @Department 新零售
 * @ClassName InstallationPersonnelController
 * @Description 运营分公司安装人员管理
 * @Author ---CWZ
 * @Date 2018/12/7 15:16
 * @Version 1.0
 **/
@Api(description =  "运营分公司安装人员管理")
@Controller
@RequestMapping("/installationPersonnel")
@AutoLog
public class InstallationPersonnelController {


    @Autowired
    private InstallationPersonnelService installationPersonnelService;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     *
     * 功能描述: 安装人员列表以及条件检索接口
     *
     * @param: InstallPersonListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    @PostMapping(value = "/list")
    @ResponseBody
    @ApiOperation("查询安装人员列表")
    public Response list(@RequestBody @Validated InstallPersonListCondition installPersonListCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("参数校验失败......"+bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return installationPersonnelService.list(installPersonListCondition);
        }
    }
    /**
     *
     * 功能描述:安装人员添加接口
     *
     * @param: InstallPersonAddCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("安装人员添加")
    public Response add(HttpServletRequest request,@RequestBody @Validated InstallPersonAddCondition installPersonAddCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("参数校验失败......"+bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return installationPersonnelService.add(request,installPersonAddCondition);
        }
    }

    /**
     *
     * 功能描述: 安装人员修改接口
     *
     * @param: InstallPersonEditCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    @ApiOperation("安装人员修改")
    public Response edit(@RequestBody @Validated InstallPersonEditCondition installPersonEditCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("参数校验失败......"+bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return installationPersonnelService.edit(installPersonEditCondition);
        }
    }

    /**
     *
     * 功能描述:安装人员删除接口
     *
     * @param: InstallPersonDeleteCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */

    @PostMapping(value = "/delete")
    @ResponseBody
    @ApiOperation("安装人员删除")
    public Response delete(@RequestBody @Validated InstallPersonDeleteCondition installPersonDeleteCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("参数校验失败......"+bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return installationPersonnelService.delete(installPersonDeleteCondition);
        }
    }

    /**
     *
     * 功能描述:安装人员重置密码接口
     *
     * @param: InstallPersonDeleteCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */

    @PostMapping(value = "/reset")
    @ResponseBody
    @ApiOperation("重置密码")
    public Response reset(@RequestBody @Validated InstallPersonDeleteCondition installPersonDeleteCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("参数校验失败......"+bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return installationPersonnelService.reset(installPersonDeleteCondition);
        }
    }
}
