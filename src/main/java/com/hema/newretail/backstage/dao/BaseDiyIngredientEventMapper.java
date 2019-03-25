package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseDiyIngredientEventEntry;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseDiyIngredientEventMapper {

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:46
     */
    int deleteBySettingId(Long id);

    /**
     *
     * 功能描述:
     *
     * @param id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:46
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:46
     */
    int insert(BaseDiyIngredientEventEntry record);

    /**
     *
     * 功能描述:
     *
     * @param record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:46
     */
    int insertSelective(BaseDiyIngredientEventEntry record);

    /**
     *
     * 功能描述:
     *
     * @param id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:46
     */
    BaseDiyIngredientEventEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:46
     */
    int updateByPrimaryKeySelective(BaseDiyIngredientEventEntry record);

    /**
     *
     * 功能描述:
     *
     * @param record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:46
     */
    int updateByPrimaryKey(BaseDiyIngredientEventEntry record);
}