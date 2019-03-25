package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseDiyAddIngredientStatusEntry;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseDiyAddIngredientStatusMapper {


    /**
     *
     * 功能描述: 
     *
     * @param  
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    List<BaseDiyAddIngredientStatusEntry> selectAll();

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    int deleteByPrimaryKey(Long id);
    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:39
     */
    int insert(BaseDiyAddIngredientStatusEntry record);
    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:39
     */
    int insertSelective(BaseDiyAddIngredientStatusEntry record);
    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:39
     */
    BaseDiyAddIngredientStatusEntry selectByPrimaryKey(Long id);
    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:39
     */
    int updateByPrimaryKeySelective(BaseDiyAddIngredientStatusEntry record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    int updateByPrimaryKey(BaseDiyAddIngredientStatusEntry record);
}