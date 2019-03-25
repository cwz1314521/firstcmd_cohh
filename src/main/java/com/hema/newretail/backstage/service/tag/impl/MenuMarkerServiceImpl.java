package com.hema.newretail.backstage.service.tag.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerCondition;
import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerListCondition;
import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerSaveCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.MenuMarkerMapper;
import com.hema.newretail.backstage.dao.RefMenuMarkerMapper;
import com.hema.newretail.backstage.entry.MenuMarker;
import com.hema.newretail.backstage.entry.RefMenuMarker;
import com.hema.newretail.backstage.model.tag.MenuMarkerBo;
import com.hema.newretail.backstage.model.tag.MenuMarkerListBo;
import com.hema.newretail.backstage.service.tag.MenuMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.tag.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-05 16:36
 */
@Service
public class MenuMarkerServiceImpl implements MenuMarkerService {
    @Autowired
    private MenuMarkerMapper menuMarkerMapper;
    @Autowired
    private RefMenuMarkerMapper refMenuMarkerMapper;

    @Override
    public Response queryList(MenuMarkerCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<MenuMarkerBo> list = menuMarkerMapper.queryList();
        return Response.success(list, ((Page) list).getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertData(MenuMarker condition) {
        condition.setGmtCreate(new Date());
        condition.setGmtModify(new Date());
        condition.setIsDeleted(0);
        menuMarkerMapper.insert(condition);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateData(MenuMarker condition) {
        condition.setGmtModify(new Date());
        menuMarkerMapper.updateByPrimaryKeySelective(condition);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteData(MenuMarker condition) {
        condition.setIsDeleted(1);
        menuMarkerMapper.updateByPrimaryKeySelective(condition);
        // TODO
        refMenuMarkerMapper.deleteByMarkerId(condition.getId());
        return Response.success();
    }

    @Override
    public Response toSaveMarkerData(MenuMarkerListCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<MenuMarkerListBo> list = refMenuMarkerMapper.queryMenusByMarkerId(condition.getId());
        return Response.success(list, ((Page) list).getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response saveMarkerData(MenuMarkerSaveCondition condition) {
        if (null != condition.getMenus()) {
            for (Long menuId : condition.getMenus()) {
                RefMenuMarker refMenuMarker = new RefMenuMarker();
                refMenuMarker.setMenuId(menuId);
                refMenuMarker.setMarkerId(condition.getId());
                refMenuMarkerMapper.insertRef(refMenuMarker);
            }
        }
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteMarkerData(RefMenuMarker condition) {
        refMenuMarkerMapper.deleteByPrimaryKey(condition.getId());
        return Response.success();
    }

    @Override
    public Response queryMenus(MenuMarker condition) {
        List<Map<String, Object>> list = refMenuMarkerMapper.queryMenus(condition.getId());
        return Response.success(list);
    }
}
