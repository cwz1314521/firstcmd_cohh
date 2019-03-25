package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.index.PopupListCondition;
import com.hema.newretail.backstage.entry.BaseMachineIndexBannerEntry;
import com.hema.newretail.backstage.model.index.banner.BannerListBo;

import java.util.List;
import java.util.Map;

/**
 * @author  cwz
 */
public interface BaseMachineIndexBannerMapper {

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Map
     * @author  cwz
     * @date  2019/3/12 10:47
     */
    List<Map> popupList(PopupListCondition condition);
    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    int insert(BaseMachineIndexBannerEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    int insertSelective(BaseMachineIndexBannerEntry record);

    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    BaseMachineIndexBannerEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param    record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    int updateByPrimaryKeySelective(BaseMachineIndexBannerEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    int updateByPrimaryKey(BaseMachineIndexBannerEntry record);

    /**
     *
     * 功能描述:
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    List<BannerListBo> selectListMap();

    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    int delete(Long id);
}