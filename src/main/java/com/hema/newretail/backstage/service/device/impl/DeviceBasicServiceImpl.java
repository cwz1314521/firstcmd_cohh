package com.hema.newretail.backstage.service.device.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.RepeatMachineTypeName;
import com.hema.newretail.backstage.common.queryparam.device.type.DeviceBasicAddCondition;
import com.hema.newretail.backstage.common.queryparam.device.type.DeviceBasicDeleteCondition;
import com.hema.newretail.backstage.common.queryparam.device.type.DeviceBasicEditCondition;
import com.hema.newretail.backstage.common.queryparam.device.type.DeviceBasicListCondition;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.StringUtil;
import com.hema.newretail.backstage.dao.BaseMachineTypeMapper;
import com.hema.newretail.backstage.entry.BaseMachineTypeEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import com.hema.newretail.backstage.service.device.DeviceBasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName DeviceBasicServiceImpl
 * @Description 设备类型基础信息 service
 * @Author ---CWZ
 * @Date 2019/1/22 9:46
 * @Version 1.0
 **/

@Service
public class DeviceBasicServiceImpl implements DeviceBasicService {




    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private BaseMachineTypeMapper baseMachineTypeMapper;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 功能描述: 设备类型添加
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/21 15:56
     */
    @Override
    @RepeatMachineTypeName
    public Response add(HttpServletRequest request, DeviceBasicAddCondition condition) {
        logger.info("新增参数类拼装");
        BaseMachineTypeEntry entry = new BaseMachineTypeEntry();
        entry.setDescription(condition.getDescription());
        entry.setGmtCreate(new Date());
        entry.setGmtModified(new Date());
        entry.setIngredientBoxNum(condition.getIngredientBoxNum());
        entry.setIsDeleted(0);
        entry.setMachineTypeCode(condition.getMachineTypeCode());
        entry.setMachineTypeName(condition.getMachineTypeName());
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if (userinfoJson == null) {
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        String user = String.valueOf(jsStr.get("userName"));
        logger.info("查询当前操作人"+user);
        entry.setOperator(user);
        entry.setPicUrl(condition.getPicUrl());
        entry.setRemark(null);
        entry.setStatus(condition.getStatus());
        logger.info("数据库新增操作");
        int insert = baseMachineTypeMapper.insert(entry);
        if(insert == 1){
            logger.info("新增成功");
            return Response.success();
        }else {
            logger.error("新增失败");
            return Response.failure();
        }
    }

    /**
     * 功能描述: 设备类型隐藏
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/21 15:56
     */
    @Override
    public Response delete(DeviceBasicDeleteCondition condition) {
        logger.info("查询是否关联设备");
        int i = baseMachineTypeMapper.selectCountByMachine(condition.getId());
        if(i > 0){
            logger.error("该类型已关联出库设备，无法删除");
            return Response.failure("该类型已关联出库设备，无法删除");
        }else {
            logger.info("执行逻辑删除操作");
            int updateDelete = baseMachineTypeMapper.updateDelete(condition.getId());
            if(updateDelete == 1){
                logger.info("逻辑删除操作成功");
                return Response.success();
            }else {
                logger.error("逻辑删除操作失败");
                return Response.failure();
            }
        }
    }

    /**
     * 功能描述: 设备类型修改
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/21 15:56
     */
    @Override
    @RepeatMachineTypeName
    public Response edit(HttpServletRequest request,DeviceBasicEditCondition condition) {
        logger.info("更新参数类拼装");
        BaseMachineTypeEntry entry = new BaseMachineTypeEntry();
        entry.setDescription(condition.getDescription());
        entry.setGmtModified(new Date());
        entry.setIngredientBoxNum(condition.getIngredientBoxNum());
        entry.setMachineTypeCode(condition.getMachineTypeCode());
        entry.setMachineTypeName(condition.getMachineTypeName());
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if (userinfoJson == null) {
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        String user = String.valueOf(jsStr.get("userName"));
        logger.info("查询当前操作人"+user);
        entry.setOperator(user);
        entry.setPicUrl(condition.getPicUrl());
        entry.setStatus(condition.getStatus());
        entry.setId(condition.getId());
        logger.info("执行更新操作");
        int update = baseMachineTypeMapper.updateByPrimaryKeySelective(entry);
        if(update == 1){
            logger.info("更新操作成功");
            return Response.success();
        }else {
            logger.error("更新操作失败");
            return Response.failure();
        }
    }

    /**
     * 功能描述: 设备类型list
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/21 15:56
     */
    @Override
    public Response list(DeviceBasicListCondition condition) {
        logger.info("分页信息拼装");
        Page<Map> page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        logger.info("执行查询");
        baseMachineTypeMapper.list(condition);
        logger.info("返回数据至前端");
        for (Map m:page.getResult()
             ) {
            List<String> list = StringUtil.stringsToString(m.get("picUrl").toString());
            m.put("picList",list);
        }
        return Response.success(page.getResult(),page.getTotal(),condition.getPageSize(),condition.getPageNum());
    }
}
