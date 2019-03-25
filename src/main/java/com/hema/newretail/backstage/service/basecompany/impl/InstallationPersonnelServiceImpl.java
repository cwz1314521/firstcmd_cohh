package com.hema.newretail.backstage.service.basecompany.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.*;
import com.hema.newretail.backstage.common.utils.Md5Util;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.SetupUserMapper;
import com.hema.newretail.backstage.entry.SetupUserEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import com.hema.newretail.backstage.service.basecompany.InstallationPersonnelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName InstallationPersonnelServiceImpl
 * @Description 安装人员管理serviceImpl
 * @Author ---CWZ
 * @Date 2018/12/7 16:10
 * @Version 1.0
 **/
@Service
public class InstallationPersonnelServiceImpl implements InstallationPersonnelService {

    @Autowired
    private SetupUserMapper setupUserMapper;

    @Autowired
    private RedisUtils redisUtils;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     * 功能描述: 安装人员列表以及条件检索接口
     *
     * @param installPersonListCondition
     * @param: InstallPersonListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    @Override
    public Response list(InstallPersonListCondition installPersonListCondition) {
        /*1.条件参数拼装*/
        InstallPersonListDBCondition condition = new InstallPersonListDBCondition();
        condition.setMobile(installPersonListCondition.getMobile());
        condition.setUsername(installPersonListCondition.getUsername());
        logger.info("参数拼接");

        /*2.分页查询操作*/
        Page<SetupUserEntry> page = PageHelper.startPage(installPersonListCondition.getPageNum(), installPersonListCondition.getPageSize());
        setupUserMapper.list(condition);

        /*3.返回前端*/
        return Response.success(page.getResult(),page.getTotal(),installPersonListCondition.getPageSize(),installPersonListCondition.getPageNum());
    }

    /**
     * 功能描述:安装人员添加接口
     *
     * @param installPersonAddCondition
     * @param: InstallPersonAddCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    @Override
    public Response add(HttpServletRequest request, InstallPersonAddCondition installPersonAddCondition) {

        /*1.添加参数类拼装*/
        logger.info("参数类拼装...");
        SetupUserEntry entry = new SetupUserEntry();
        entry.setGmtCreate(new Date());
        entry.setGmtModified(new Date());

        /*1.1 根据session从redis检索数据*/
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if(userinfoJson == null){
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        long companyId = Long.valueOf(String.valueOf(jsStr.get("companyId"))).longValue();
        logger.info("从缓存中拿到companyId ：" + companyId);
        entry.setComponyId(companyId);
        entry.setStatus(0);
        entry.setUsername(installPersonAddCondition.getUsername());

        /*1.2根据前端传过来的密码进行md5加密*/
        logger.info("md5加密密码...");
        String md5 = Md5Util.getMD5(installPersonAddCondition.getMobile() + installPersonAddCondition.getPassword() + "machine_setup");
        entry.setPassword(md5);
        logger.info("检索是否有重复数据..."+entry.getMobile());
        entry.setMobile(installPersonAddCondition.getMobile());
        /*2.修改操作*/
        /***
         * 检查是否重名
         */
        UsernameCondition usernameCondition = new UsernameCondition();
        usernameCondition.setMobile(entry.getMobile());
        int i = setupUserMapper.countMobile(usernameCondition);
        /*2.1修改操作数据库返回参数处理并返回前端*/
        if(i>0){
            logger.error("账号重复....");
            return  Response.failure("账号重复....请更换");
        }else {
            int insert = setupUserMapper.insert(entry);
            if(insert == 1){
                return  Response.success("操作成功");
            }else {
                logger.error("操作失败....");
                return  Response.failure("操作失败");
            }
        }
    }

    /**
     * 功能描述:
     *
     * @param installPersonAddCondition
     * @param: InstallPersonEditCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    @Override
    public Response edit(InstallPersonEditCondition installPersonAddCondition) {

        /*1.参数拼装*/
        SetupUserEntry entry = new SetupUserEntry();
        logger.info("更新参数类拼装...." + installPersonAddCondition.toString());
        entry.setUsername(installPersonAddCondition.getUsername());
        entry.setMobile(installPersonAddCondition.getMobile());
        entry.setGmtModified(new Date());
        entry.setId(installPersonAddCondition.getId());
        logger.info("更新操作...");

        /*2.更新操作并处理返回参数返回前端*/
        /***
         * 检查是否重名
         */
        UsernameCondition usernameCondition = new UsernameCondition();
        usernameCondition.setMobile(entry.getMobile());
        usernameCondition.setId(entry.getId());
        int i = setupUserMapper.countMobile(usernameCondition);
        if(i>0){
            logger.error("账号重复....");
            return  Response.failure("账号重复....请更换");
        }else {
            int update = setupUserMapper.update(entry);
            if (update < 1) {
                logger.error("更新操作失败");
                return Response.failure("更新操作失败");
            } else if (update == 1) {
                logger.info("更新操作成功" + entry.toString());
                return Response.success();
            } else {
                logger.error("更新操作失败,数据异常");
                return Response.failure("更新操作失败,数据异常");
            }
        }
    }

    /**
     * 功能描述:
     *
     * @param: InstallPersonDeleteCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    @Override
    public Response delete(InstallPersonDeleteCondition condition) {

        int delete = setupUserMapper.deleteByPrimaryKey(condition.getId());
        if(delete < 1){
            logger.error("删除操作失败");
            return Response.failure("删除操作失败");
        }else if(delete == 1){
            logger.info("删除操作成功");
            return Response.success();
        }else{
            logger.error("删除操作失败,数据异常");
            return Response.failure("删除操作失败,数据异常");
        }
    }

    /**
     * 功能描述:安装人员重置密码接口
     *
     * @param: InstallPersonDeleteCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    @Override
    public Response reset(InstallPersonDeleteCondition personCondition) {
        /*1.参数拼装*/
        SetupUserEntry setupUserEntry = setupUserMapper.selectByPrimaryKey(personCondition.getId());
        /*1.1查询数据库取出本条数据其他信息*/
        if(setupUserEntry ==null){
            return Response.failure("重置密码操作失败，本条数据不存在");
        }
        /*1.2 根据约定初始密码生成MD5加密字段*/
        SetupUserEntry entry = new SetupUserEntry();
        logger.info("重置密码参数类拼装...." + personCondition.toString());
        entry.setGmtModified(new Date());
        entry.setId(personCondition.getId());
        logger.info("md5加密密码...");
        String md5 = Md5Util.getMD5(setupUserEntry.getMobile() + "123456" + "machine_setup");
        entry.setPassword(md5);
        logger.info("重置密码操作...默认密码为123456");
        /*2.更新操作并处理返回参数返回前端*/
        int delete = setupUserMapper.reset(entry);
        if(delete < 1){
            logger.error("重置密码操作失败");
            return Response.failure("重置密码操作失败");
        }else if(delete == 1){
            logger.info("重置密码操作成功" +entry.toString());
            return Response.success();
        }else{
            logger.error("重置密码操作失败,数据异常");
            return Response.failure("重置密码操作失败,数据异常");
        }
    }
}
