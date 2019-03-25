package com.hema.newretail.backstage.service.tag;

import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerCondition;
import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerListCondition;
import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerSaveCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.MenuMarker;
import com.hema.newretail.backstage.entry.RefMenuMarker;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.tag
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-05 16:35
 */
public interface MenuMarkerService {
    /**
     * 查询
     *
     * @param condition
     * @return
     */
    Response queryList(MenuMarkerCondition condition);

    /**
     * 添加
     *
     * @param condition
     * @return
     */
    Response insertData(MenuMarker condition);

    /**
     * 编辑
     *
     * @param condition
     * @return
     */
    Response updateData(MenuMarker condition);

    /**
     * 逻辑删除
     *
     * @param condition
     * @return
     */
    Response deleteData(MenuMarker condition);

    /**
     * 查看角标关联的饮品
     *
     * @param condition
     * @return
     */
    Response toSaveMarkerData(MenuMarkerListCondition condition);

    /**
     * 关联饮品
     *
     * @param condition
     * @return
     */
    Response saveMarkerData(MenuMarkerSaveCondition condition);

    /**
     * 删除一条记录
     *
     * @param condition
     * @return
     */
    Response deleteMarkerData(RefMenuMarker condition);

    /**
     * 查询角标未分配的所有饮品
     *
     * @param condition
     * @return
     */
    Response queryMenus(MenuMarker condition);
}
