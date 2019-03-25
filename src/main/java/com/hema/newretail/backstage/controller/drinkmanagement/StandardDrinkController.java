package com.hema.newretail.backstage.controller.drinkmanagement;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.menu.BusiIngredientMenuImageTextCondition;
import com.hema.newretail.backstage.common.queryparam.menu.MenuImageTextCondition;
import com.hema.newretail.backstage.common.queryparam.menu.UpdateRecommendCondition;
import com.hema.newretail.backstage.common.requestparam.MenuParam;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.UploadFileUtil;
import com.hema.newretail.backstage.common.validator.First;
import com.hema.newretail.backstage.common.validator.Second;
import com.hema.newretail.backstage.entry.BusiIngredientMenuEntry;
import com.hema.newretail.backstage.entry.BusiIngredientMenuImageText;
import com.hema.newretail.backstage.enums.ResultCode;
import com.hema.newretail.backstage.service.drinkmanagement.StandardDrinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiahao on 2018-08-25
 */
@Api(description = "标准饮品")
@RestController
@RequestMapping("/standardDrink")
@AutoLog
public class StandardDrinkController {

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";

    @Autowired
    StandardDrinkService standardDrinkService;

    @ApiOperation("校验饮品名称")
    @PostMapping("/verificationName")
    public Response verificationAddStandardDrinkMenuName(@RequestParam String menuName, @RequestParam Integer type, @RequestParam Long id) {
        String standardDrink = null;
        if (type == 1) {
            standardDrink = standardDrinkService.selectStandardDrink(menuName, id);
        } else {
            BusiIngredientMenuEntry busiIngredientMenuEntry = standardDrinkService.selectStandardDrink(menuName);
            if (busiIngredientMenuEntry != null) {
                return Response.failure(ResultCode.VALIDATION_ERROR_MENU_NAME_NOT_NULL);
            }
        }
        if (OK.equals(standardDrink)) {
            return Response.success("自身相同修改");
        }
        if (ERROR.equals(standardDrink)) {
            return Response.failure(ResultCode.VALIDATION_ERROR_MENU_NAME_NOT_NULL);
        }
        return Response.success("允许修改");
    }

    @ApiOperation("上传饮品图片")
    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam(required = false) Integer proportionType, MultipartFile file) {
        String uploadImageOss = UploadFileUtil.uploadImageOss(file, proportionType);
        if (uploadImageOss == null) {
            return Response.failure();
        }
        boolean httpUrl = isHttpUrl(uploadImageOss);
        if (!httpUrl) {
            return Response.failure(uploadImageOss);
        }
        return Response.success(uploadImageOss);
    }

    @ApiOperation("添加与编辑标准饮品")
    @PostMapping("/addAndUpdateStandardDrink")
    public Response addStandardDrink(@RequestBody @Valid MenuParam menuParam) {
        if (menuParam == null) {
            return Response.failure(ResultCode.VALIDATION_ERROR_PARAM_CODE_EMPTY);
        }
        standardDrinkService.saveAndUpdateStandardDrink(menuParam);

        return Response.success();
    }

    /**
     * 设置首页推荐
     *
     * @param menuParam 参数
     * @return Response
     * @author ZhangHaiSheng
     */
    @ApiOperation("设置首页推荐")
    @PostMapping("/updateRecommend")
    public Response updateRecommend(@RequestBody @Validated UpdateRecommendCondition menuParam) {
        return standardDrinkService.updateRecommend(menuParam);
    }

    @ApiOperation("查看饮品图文")
    @PostMapping("/imageTextDetail")
    public Response imageTextDetail(@RequestBody @Validated({Second.class}) MenuImageTextCondition condition) {
        return standardDrinkService.imageTextDetail(condition);
    }

    @ApiOperation("添加/编辑饮品图文")
    @PostMapping("/imageTextAddOrEdit")
    public Response imageTextAddOrEdit(@RequestBody @Validated({First.class, Second.class}) BusiIngredientMenuImageTextCondition condition) {
        return standardDrinkService.updateMenuImageText(condition);
    }

    @AutoLog(false)
    private static boolean isHttpUrl(String urls) {
        boolean isurl;
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";

        Pattern pat = Pattern.compile(regex.trim());
        Matcher mat = pat.matcher(urls.trim());
        isurl = mat.matches();
        if (isurl) {
            isurl = true;
        }
        return isurl;
    }
}
