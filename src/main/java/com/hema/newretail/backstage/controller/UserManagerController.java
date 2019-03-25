package com.hema.newretail.backstage.controller;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.usermanagementparameter.UserManaCondition;
import com.hema.newretail.backstage.common.utils.excel.ExcelUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.entry.UserManagerData;
import com.hema.newretail.backstage.entry.PushInfoData;
import com.hema.newretail.backstage.entry.UserFormIdData;
import com.hema.newretail.backstage.service.IUserManagerService;
import com.hema.newretail.backstage.service.IUserPushInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * 用户管理Controller
 */
@Api(description = "用户管理")
@RestController
@RequestMapping(value = "/userManager")
@AutoLog
public class UserManagerController {

    private Logger logger = LoggerFactory.getLogger(UserManagerController.class);

    @Autowired
    private IUserManagerService userManagerService;

    @Autowired
    private IUserPushInfoService userPushInfoService;

    @Value(value = "${excelModel.srcFileName}")
    private String srcFileName;

    private static final String COMMA  = ",";

    /**
     * 会员详情接口
     * @param id
     * @return
     */
    @ApiOperation(value = "查看会员详情")
    @RequestMapping(value = "/userComsumptionDetail",method = RequestMethod.POST)
    @ResponseBody
    public Response queryUserConsumptionDetail(String id){
        UserManagerData user = userManagerService.queryOneById(id);

        return Response.success(user);
    }

    /**
     * 根据id更新会员账号状态
     * @param id
     * @return
     */
    @ApiOperation(value = "更新会员账号状态")
    @RequestMapping(value = "/updateUserStatusById",method = RequestMethod.POST)
    @ResponseBody
    public Response updateUserStatusById(String id){
        if(userManagerService.updateStatusById(id)){
            return Response.success();
        }else {
            return Response.failure();
        }
    }

    /**
     * 会员注销接口
     * @param id
     * @return
     */
    @ApiOperation(value = "会员注销")
    @RequestMapping(value = "/logoutUser",method = RequestMethod.POST)
    @ResponseBody
    public Response logoutUser(String id){
        return Response.success();
    }

    /**
     * 会员消费记录接口
     * @param params{openId:String,pageNum:int,pageSize:int}都是必填项
     * @return
     */
    @ApiOperation(value = "查看会员消费记录")
    @RequestMapping(value = "/userConsumptions",method = RequestMethod.POST)
    @ResponseBody
    public Response userConsumptionsByOpenId(@RequestBody Map<String,Object> params){

        return userManagerService.getUserConsumptionsByOpenId(params);
    }

    /**
     * 推送信息列表接口
     * @param params{pageNum:int,pageSize:int}都是必填项
     * @return
     */
    @ApiOperation(value = "推送信息")
    @PostMapping(value = "/pushInfoAll")
    @ResponseBody
    public Response getUserPushInfoAll(@RequestBody Map<String,Object> params){
        return userPushInfoService.findAll(params);
    }

    /**
     * 推送信息详情接口
     * @param id
     * @return
     */
    @ApiOperation(value = "推送信息详情")
    @PostMapping(value = "/pushInfoById")
    @ResponseBody
    public Response getUserPushInfoById(@RequestBody String id){
        return Response.success(userPushInfoService.findOne(id));
    }

    /**
     * 推送信息保存接口
     * @param data
     */
    @ApiOperation(value = "保存推送信息")
    @PostMapping(value = "/pushInfoToUser")
    @ResponseBody
    public void pushInfoToUser(@RequestBody PushInfoData data){
        userPushInfoService.save(data);
    }

    /**
     * 保存formId测试接口
     * @param data
     */
    @ApiOperation(value = "保存前端formId")
    @PostMapping(value = "/saveUserFormId")
    @ResponseBody
    public void saveUserFormId(@RequestBody UserFormIdData data){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK,-7);
        Date beginTime = c.getTime();
        data.setCreateTime(beginTime);
        userPushInfoService.saveFormId(data);
    }

    /**
     * 导出会员信息Excel接口
     * @param userManaCondition
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "导出会员信息Excel",produces="application/octet-stream")
    @GetMapping(value = "/exportUserInfoExcel")
    @ResponseBody
    public Response downUserManagerExcel(UserManaCondition userManaCondition, HttpServletResponse response) throws Exception {

        List<UserManagerData> userData = userManagerService.findAll(userManaCondition);

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");

        String fileName = "用户数据导出"+ TimeUtil.getStringByDateFormart(new Date(),"yyyyMMddHHmmss")+".xlsx";
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        response.flushBuffer();
        ExcelUtils.exportExcel(srcFileName,userData,response.getOutputStream());
        return Response.success();
    }

    /**
     * 发送推送消息，并保存推送信息
     * @param reqMap 包含users，title,content 其中users由用户信息id组合中间用“，”隔开
     * @return
     */
    @ApiOperation(value = "发送推送消息，并保存推送信息")
    @PostMapping(value = "/pushInfoToUsers")
    @ResponseBody
    public Response pushInfoToUsers(@RequestBody Map<String,String> reqMap){
        if(reqMap.size()>0){
            String users = reqMap.get("users");
            String title = reqMap.get("title");
            String content = reqMap.get("content");
            String isWeiXin = reqMap.get("isWeiXin");

            String[] fillData = {TimeUtil.getStringByDateFormart(new Date(),"MM月dd日 HH:mm"),content};

            String[] idArr;
            if (users.indexOf(COMMA)>0){
                idArr = users.split(",");
            }else{
                idArr = new String[]{users};
            }
            if(isWeiXin != null && !"".equals(isWeiXin)){
                if(Integer.parseInt(isWeiXin)==1){
                    List<UserManagerData> userList = userManagerService.queryAllByIds(Arrays.asList(idArr));

                    for (UserManagerData user:userList){
                        String openId = user.getOpenId();
                        if(openId!=null && !"".equals(openId)){
                            UserFormIdData formIdData = userPushInfoService.findOneByOpenId(openId);
                            if(formIdData!=null && formIdData.getFormId() != null){
                                String result = userPushInfoService.sendTemplate(openId,formIdData.getFormId(),fillData);
                                logger.info("消息发送结果："+result);
                            }else {
                                logger.info("该用户的formId是空，openId:"+openId);
                            }
                        }else {
                            logger.info("此用户没有openID，用户ID："+user.getId());
                        }
                    }
                }
            }else {
                logger.info("是否发送微信服务参数错误");
            }


            //Todo 获取当前登录的用户
            String operator = "admin";

            //组装信息保存
            PushInfoData pushInfoData = new PushInfoData();
            pushInfoData.setContent(content);
            pushInfoData.setTitle(title);
            pushInfoData.setOperator(operator);
            pushInfoData.setIsWeiXin(isWeiXin);
            pushInfoData.setUsers(Arrays.asList(idArr));
            pushInfoData.setPushTime(new Date());
            userPushInfoService.save(pushInfoData);
            return Response.success();
        }else {
            return Response.failure("参数不能为空");
        }

    }

}
