package com.hema.newretail.backstage.service.financial.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.hema.newretail.backstage.common.queryparam.financial.MenuProfitCondition;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.BusiIngredientMenuProfitMapper;
import com.hema.newretail.backstage.dao.IngredientMenuEntryMapper;
import com.hema.newretail.backstage.entry.BaseUserInfoEntry;
import com.hema.newretail.backstage.entry.BusiIngredientMenuProfit;
import com.hema.newretail.backstage.entry.IngredientMenuEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import com.hema.newretail.backstage.model.financial.MenuProfitBo;
import com.hema.newretail.backstage.service.financial.MenuProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.financial.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-07 15:04
 */
@Service
public class MenuProfitServiceImpl implements MenuProfitService {
    @Autowired
    private BusiIngredientMenuProfitMapper busiIngredientMenuProfitMapper;
    @Autowired
    private IngredientMenuEntryMapper ingredientMenuEntryMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Response queryList(MenuProfitCondition vo) {
        List<MenuProfitBo> list = busiIngredientMenuProfitMapper.selectList(vo, vo.getPageNum(), vo.getPageSize());
        return Response.success(list, ((Page) list).getTotal(), vo.getPageSize(), vo.getPageNum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insert(BusiIngredientMenuProfit vo, HttpSession session) {
        BaseUserInfoEntry userInfo = getUserInfo(session.getId());
        if (null == userInfo || null == userInfo.getPostId()) {
            return Response.failureValid("获取登录信息失败，请重新登录");
        }
        vo.setOperator(userInfo.getRealName());
        vo.setGmtCreate(new Date());
        vo.setGmtModified(new Date());
        vo.setIsDeleted(false);
        vo.setRemark("");
        busiIngredientMenuProfitMapper.insert(vo);

        IngredientMenuEntry menu = new IngredientMenuEntry();
        menu.setPrice(vo.getSalePrice());
        // id=0 是DIY
        menu.setId(vo.getIngredientMenuId());
        menu.setGmtModified(new Date());
        ingredientMenuEntryMapper.updatePriceByPrimaryKey(menu);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response update(BusiIngredientMenuProfit vo, HttpSession session) {
        BaseUserInfoEntry userInfo = getUserInfo(session.getId());
        if (null == userInfo || null == userInfo.getPostId()) {
            return Response.failureValid("获取登录信息失败，请重新登录");
        }
        vo.setGmtModified(new Date());
        vo.setOperator(userInfo.getRealName());
        busiIngredientMenuProfitMapper.updateByPrimaryKeySelective(vo);

        IngredientMenuEntry menu = new IngredientMenuEntry();
        menu.setPrice(vo.getSalePrice());
        // id=0 是DIY
        menu.setId(vo.getIngredientMenuId());
        menu.setGmtModified(new Date());
        ingredientMenuEntryMapper.updatePriceByPrimaryKey(menu);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response stop(BusiIngredientMenuProfit vo) {
        vo.setGmtModified(new Date());
        if (!vo.getIsDeleted()) {
            Long num = busiIngredientMenuProfitMapper.selectNum(vo);
            if (0 == num) {
                return Response.failure("该规则时间已失效，请新添加分润规则");
            }
        }
        if(!vo.getIsDeleted()) {
            BusiIngredientMenuProfit profit = busiIngredientMenuProfitMapper.selectByPrimaryKey(vo.getId());
            busiIngredientMenuProfitMapper.updateIsDeleted(profit);
        }
        busiIngredientMenuProfitMapper.updateByPrimaryKeySelective(vo);
        return Response.success();
    }

    @Override
    public Response menus() {
        return Response.success(ingredientMenuEntryMapper.selectAllMenus());
    }

    /**
     * 获取当前登录用户信息
     *
     * @param sessionId
     * @return
     */
    private BaseUserInfoEntry getUserInfo(String sessionId) {
        String userInfoJson = redisUtils.hget(AuthConstants.SESSION + sessionId, AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if (StringUtils.isEmpty(userInfoJson)) {
            return null;
        }
        BaseUserInfoEntry userInfo = JSON.parseObject(userInfoJson, BaseUserInfoEntry.class);
        if (null == userInfo || null == userInfo.getPostId()) {
            return null;
        }
        return userInfo;
    }
}
