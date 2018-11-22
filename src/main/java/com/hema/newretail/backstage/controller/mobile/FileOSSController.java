package com.hema.newretail.backstage.controller.mobile;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.mobile.IngredientNameByIdCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.UploadFileUtil;
import com.hema.newretail.backstage.service.mobile.FileOSSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @Department 新零售
 * @ClassName FileOSSController
 * @Description 提供给文件中转操作
 * @Author ---CWZ
 * @Date 2018/11/16 10:16
 * @Version 1.0
 **/
@Api(description =  "≡(▔﹏▔)≡提供给文件中转操作接口")
@Controller
@RequestMapping("/mobile")
public class FileOSSController {


    @Autowired
    private FileOSSService fileOSSService;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     *
     * 功能描述: 根据原料id查询原料名字 ---  加入redis缓存
     *
     * @param: IngredientNameByIdCondition
     * @return: Response
     * @author: cwz
     * @date: 2018/11/16 10:22
     */
    @ApiOperation("≡(▔﹏▔)≡根据原料id查询原料名字")
    @PostMapping("/ingredientNameById")
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
    @ApiOperation("≡(▔﹏▔)≡机器日志上传")
    @PostMapping("/uploadTxt")
    @ResponseBody
    public  Response uploadTxt(@RequestParam("file") File file){

//        File file = (ResourceUtils.getFile("E:/github/测试上传1.txt"));
                    // 上传到OSS
                    String url = UploadFileUtil.uploadLocalFile(file, "log/",file.getName());
                    if (url == null) {
                        //TODO:上传失败的业务处理
                        return Response.failure("上传失败");
                    }
                    logger.info("上传完毕,访问地址:"+url);
                    return Response.success(url);
    }
}
