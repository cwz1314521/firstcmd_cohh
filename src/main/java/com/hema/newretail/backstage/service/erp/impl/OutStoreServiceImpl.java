package com.hema.newretail.backstage.service.erp.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.erp.InStoreDBCondition;
import com.hema.newretail.backstage.common.queryparam.erp.InStoreListCondition;
import com.hema.newretail.backstage.common.queryparam.erp.InStoreNumCondition;
import com.hema.newretail.backstage.common.queryparam.erp.InStorePreAllCondition;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.ErpOrderQrcodeMapper;
import com.hema.newretail.backstage.entry.erp.ErpOrderQrcodeEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import com.hema.newretail.backstage.model.erp.InStoreListBo;
import com.hema.newretail.backstage.model.erp.InStoreTodayBo;
import com.hema.newretail.backstage.service.erp.OutStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName OutStoreServiceImpl
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/11/27 14:10
 * @Version 1.0
 **/
@Service
public class OutStoreServiceImpl implements OutStoreService {
    @Autowired
    private ErpOrderQrcodeMapper erpOrderQrcodeMapper;
    @Autowired
    private RedisUtils redisUtils;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private static final String EMPTY = "";
    private static final Integer AFOUR = -4;
    private static final Integer ZTWO = 2;

    /**
     * 功能描述:分后台  入库记录--待入库列表
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    @Override
    public Response inStoreLoadList() {
        List<InStoreTodayBo> inStoreTodayBos = erpOrderQrcodeMapper.selectOutStoreNowMap();
        if(inStoreTodayBos == null){
            logger.error("暂无数据");
            return  Response.failure("暂无数据");
        }
        return Response.success(inStoreTodayBos);
    }

    /**
     * 功能描述: 分后台---待出库--订单列表
     *
     * @param:
     * @return:
     * @author: admin
     * @date: 2018/11/27 11:43
     */
    @Override
    public Response waitOutstoreList(InStoreListCondition inStoreListCondition) {

        logger.info("拼装分页信息......页码 "+inStoreListCondition.getPageNum()+",每页最大数 "+inStoreListCondition.getPageSize());
        Page<InStoreListBo> page =PageHelper.startPage(inStoreListCondition.getPageNum(),inStoreListCondition.getPageSize());
        erpOrderQrcodeMapper.selectBaseOutStoreListMap();
        return Response.success(page.getResult(),page.getTotal(),inStoreListCondition.getPageSize(),inStoreListCondition.getPageNum());
    }

    /**
     * 功能描述: 分后台---今日出库--列表
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/27 11:43
     */
    @Override
    public Response todayOutstore() {
        List<InStoreTodayBo> inStoreTodayBos = erpOrderQrcodeMapper.selectOutStoreTodayMap();
        return Response.success(inStoreTodayBos);
    }


    /**
     * 功能描述: 分后台--扫码出库
     *
     * @param inStoreListCondition
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    @Override
    public Response outstorePre(InStorePreAllCondition inStoreListCondition) {
        logger.info("第一步更新扫码枪扫到的数据......");
        if(inStoreListCondition.getRandomCode() != null  && !EMPTY.equals(inStoreListCondition.getRandomCode())){
            int num = 0;
            for (String randomCode:inStoreListCondition.getRandomCode()
                    ) {
                num += erpOrderQrcodeMapper.updateByQrcodeCodeOut(randomCode);
                logger.info("循环更新待出库状态......"+randomCode);
            }
            if(num > 0) {
                return Response.success();
            } else {
                return Response.failure("未检测到有效的溯源码");
            }
        }else{
            return Response.failure("未检测到数据");
        }
    }

    /**
     * 功能描述: 分后台--输溯源码出库
     *
     * @param request
     * @param inStoreNumCondition
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    @Override
    public Response outstoreNum(HttpServletRequest request, InStoreNumCondition inStoreNumCondition) {
        ErpOrderQrcodeEntry entry = erpOrderQrcodeMapper.selectByQrcodeCodeOut(inStoreNumCondition.getRandomCode());
        if(entry == null){
            return Response.failure("未检测到该溯源码");
        }else{
            if(AFOUR.equals(entry.getStatus())){
                return Response.failure("该溯源码已录入待出库");
            }else  if(ZTWO.equals(entry.getStatus())){
                return Response.failure("该溯源码已出库");
            }else {
                int i = erpOrderQrcodeMapper.updateByQrcodeCodeOut(inStoreNumCondition.getRandomCode());
                if(i>0) {
                    return Response.success("该溯源码已录入待出库");
                }else {
                    return Response.failure("未检测到该溯源码");
                }
            }
        }
    }

    /**
     * 功能描述: 分后台-- 确认提交出库
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    @Override
    public Response outstore(HttpServletRequest request) {
        List<InStoreTodayBo> inStoreTodayBos = erpOrderQrcodeMapper.selectOutStoreNowMap();
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if(userinfoJson == null){
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        long userId = Long.valueOf(String.valueOf(jsStr.get("id"))).longValue();
        logger.info("拼装出库参数");
        InStoreDBCondition condition = new InStoreDBCondition();
        condition.setTime(new Date());
        condition.setUserId(userId);
        int i = erpOrderQrcodeMapper.updateByOutStoreDBCondition(condition);
        if(i>0) {
            return Response.success(inStoreTodayBos);
        }else {
            return Response.failure("待出库暂无数据，请先扫码上传数据");
        }
    }
}
