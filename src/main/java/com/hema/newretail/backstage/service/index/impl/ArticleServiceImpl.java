package com.hema.newretail.backstage.service.index.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.index.ArticleAddCondition;
import com.hema.newretail.backstage.common.queryparam.index.ArticleDeleteCondition;
import com.hema.newretail.backstage.common.queryparam.index.ArticleEditCondition;
import com.hema.newretail.backstage.common.queryparam.index.ArticleListCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.dao.BaseIndexArticleMapper;
import com.hema.newretail.backstage.entry.BaseIndexArticleEntry;
import com.hema.newretail.backstage.service.index.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Department 新零售
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/26 10:51
 * @Version 1.0
 **/

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private BaseIndexArticleMapper baseIndexArticleMapper;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     * 功能描述: 首页文章列表
     *
     * @param condition
     * @return
     * @author cwz
     * @date 2019/2/25 15:13
     */
    @Override
    public Response list(ArticleListCondition condition) {
        Page<BaseIndexArticleEntry> page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        baseIndexArticleMapper.list(condition);
        return  Response.success(page.getResult(),page.getTotal(),page.getPageSize(),page.getPageNum());
    }

    /**
     * 功能描述: 首页文章添加
     *
     * @param condition
     * @return
     * @author cwz
     * @date 2019/2/25 15:13
     */
    @Override
    public Response add(ArticleAddCondition condition) throws Exception{
        BaseIndexArticleEntry baseIndexArticleEntry = new BaseIndexArticleEntry();
        baseIndexArticleEntry.setArticleAbstract(condition.getArticleAbstract());
        baseIndexArticleEntry.setGmtCreate(new Date());
        baseIndexArticleEntry.setGmtEnd(TimeUtil.getDate(condition.getGmtEnd()));
        baseIndexArticleEntry.setGmtModify(new Date());
        baseIndexArticleEntry.setGmtStart(TimeUtil.getDate(condition.getGmtStart()));
        baseIndexArticleEntry.setInfo(condition.getInfo());
        baseIndexArticleEntry.setIsDelete("0");
        baseIndexArticleEntry.setPicUrl(condition.getPicUrl());
        baseIndexArticleEntry.setSkipUrl(condition.getSkipUrl());
        baseIndexArticleEntry.setSort(condition.getSort());
        baseIndexArticleEntry.setStatus(condition.getStatus());
        baseIndexArticleEntry.setTitle(condition.getTitle());
        baseIndexArticleEntry.setType(condition.getType());
        int insert = baseIndexArticleMapper.insert(baseIndexArticleEntry);
        if(insert == 1){
            return Response.success();
        }else {
            return Response.failure();
        }

    }

    /**
     * 功能描述: 首页文章修改
     *
     * @param condition
     * @return
     * @author cwz
     * @date 2019/2/25 15:13
     */
    @Override
    public Response edit(ArticleEditCondition condition) throws Exception {
        BaseIndexArticleEntry baseIndexArticleEntry = new BaseIndexArticleEntry();
        baseIndexArticleEntry.setId(condition.getId());
        baseIndexArticleEntry.setArticleAbstract(condition.getArticleAbstract());
        baseIndexArticleEntry.setGmtEnd(TimeUtil.getDate(condition.getGmtEnd()));
        baseIndexArticleEntry.setGmtModify(new Date());
        baseIndexArticleEntry.setGmtStart(TimeUtil.getDate(condition.getGmtStart()));
        baseIndexArticleEntry.setInfo(condition.getInfo());
        baseIndexArticleEntry.setPicUrl(condition.getPicUrl());
        baseIndexArticleEntry.setSkipUrl(condition.getSkipUrl());
        baseIndexArticleEntry.setSort(condition.getSort());
        baseIndexArticleEntry.setStatus(condition.getStatus());
        baseIndexArticleEntry.setTitle(condition.getTitle());
        baseIndexArticleEntry.setType(condition.getType());
        baseIndexArticleEntry.setIsDelete("0");
        int insert = baseIndexArticleMapper.updateByPrimaryKeySelective(baseIndexArticleEntry);
        if(insert == 1){
            return Response.success();
        }else {
            return Response.failure();
        }
    }

    /**
     * 功能描述: 首页文章删除
     *
     * @param condition
     * @return
     * @author cwz
     * @date 2019/2/25 15:13
     */
    @Override
    public Response delete(ArticleDeleteCondition condition) {
        int i = baseIndexArticleMapper.updateDelete(condition.getId());
        if(i == 1){
            return Response.success();
        }else {
            return Response.failure();
        }
    }
}
