package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.RefMenuMarker;
import com.hema.newretail.backstage.model.tag.MenuMarkerListBo;

import java.util.List;
import java.util.Map;

public interface RefMenuMarkerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RefMenuMarker record);

    int insertSelective(RefMenuMarker record);

    RefMenuMarker selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RefMenuMarker record);

    int updateByPrimaryKey(RefMenuMarker record);

    /**
     * 根据饮品角标ID删除数据
     *
     * @param id
     * @return
     */
    int deleteByMarkerId(Long id);

    /**
     * 查询角标关联的饮品
     *
     * @param markerId
     * @return
     */
    List<MenuMarkerListBo> queryMenusByMarkerId(Long markerId);

    /**
     * @param record
     * @return
     */
    int insertRef(RefMenuMarker record);

    /**
     * 查询角标未分配的所有饮品
     *
     * @param markerId
     * @return
     */
    List<Map<String, Object>> queryMenus(Long markerId);
}