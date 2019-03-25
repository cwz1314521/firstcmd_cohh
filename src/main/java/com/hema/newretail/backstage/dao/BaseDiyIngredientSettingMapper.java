package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.diy.NameCondition;
import com.hema.newretail.backstage.entry.BaseDiyIngredientSettingEntry;
import com.hema.newretail.backstage.model.diy.DetailEventBo;
import com.hema.newretail.backstage.model.diy.IngredientEventBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseDiyIngredientSettingMapper {



    /**
     *
     * 功能描述:
     *
     * @param   id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:47
     */
    DetailEventBo selectDetail(@Param("id") Long id);

    /**
     *
     * 功能描述:
     *
     * @param nameCondition
     * @return
     * @author  cwz
     * @date  2019/1/24 13:47
     */
    List<IngredientEventBo> selectAllEvent(NameCondition nameCondition);

    /**
     *
     * 功能描述:
     *
     * @param id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:47
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:47
     */
    int insert(BaseDiyIngredientSettingEntry record);

    /**
     *
     * 功能描述:
     *
     * @param record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:47
     */
    int insertSelective(BaseDiyIngredientSettingEntry record);

    /**
     *
     * 功能描述:
     *
     * @param id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:47
     */
    BaseDiyIngredientSettingEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:47
     */
    int updateByPrimaryKeySelective(BaseDiyIngredientSettingEntry record);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:47
     */
    int updateByPrimaryKey(BaseDiyIngredientSettingEntry record);
}