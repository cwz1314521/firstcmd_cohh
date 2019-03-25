package com.hema.newretail.backstage.controller.device;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.device.type.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.UploadFileUtil;
import com.hema.newretail.backstage.service.device.DeviceBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName DeviceBasicController
 * @Description 设备类型基础信息Controller
 * @Author ---CWZ
 * @Date 2019/1/21 15:44
 * @Version 1.0
 **/

@Api(description = "设备类型管理")
@Controller
@RequestMapping("/deviceTypeBasic")
@AutoLog
public class DeviceBasicController {

    @Autowired
    private DeviceBasicService deviceBasicService;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     *
     * 功能描述: 设备类型添加
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/21 15:56
     */
    @PostMapping(value = "/add")
    @ApiOperation("添加设备类型")
    @ResponseBody
    public Response add(HttpServletRequest request, @RequestBody @Validated DeviceBasicAddCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return deviceBasicService.add(request,condition);
        }
    }


    /**
     *
     * 功能描述: 设备类型隐藏
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/21 15:56
     */
    @PostMapping(value = "/delete")
    @ApiOperation("隐藏设备类型")
    @ResponseBody
    public Response delete( @RequestBody @Validated DeviceBasicDeleteCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return deviceBasicService.delete(condition);
        }
    }

    /**
     *
     * 功能描述: 设备类型修改
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/21 15:56
     */
    @PostMapping(value = "/edit")
    @ApiOperation("修改设备类型")
    @ResponseBody
    @AutoLog
    public Response edit(HttpServletRequest request, @RequestBody @Validated DeviceBasicEditCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return deviceBasicService.edit(request, condition);
        }
    }

    /**
     *
     * 功能描述: 设备类型list
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/21 15:56
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询设备类型列表")
    @ResponseBody
    public Response list( @RequestBody @Validated DeviceBasicListCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return deviceBasicService.list(condition);
        }
    }

    @ApiOperation("上传图片服务")
    @GetMapping ("/upload")
    @ResponseBody
    @AutoLog(value = false)
    public Map uploadFile(MultipartFile file) {
        Map<String,String> map = new HashMap<>(4);
        String uploadImageOss = UploadFileUtil.uploadImageOss(file, 4);
        map.put("original",uploadImageOss);
        if(uploadImageOss == null){
            map.put("state","FALSE");
        }else {
            map.put("state","SUCCESS");
        }
        map.put("title",uploadImageOss);
        map.put("url",uploadImageOss);
        return map;
    }
}
