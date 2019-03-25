package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.MenuMarker;
import com.hema.newretail.backstage.model.tag.MenuMarkerBo;

import java.util.List;

public interface MenuMarkerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuMarker record);

    int insertSelective(MenuMarker record);

    MenuMarker selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuMarker record);

    int updateByPrimaryKey(MenuMarker record);

    /**
     * 查询
     *
     * @return
     */
    List<MenuMarkerBo> queryList();
}