package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.agent.CountNameCondition;
import com.hema.newretail.backstage.entry.BaseBoxGroupEntry;
import com.hema.newretail.backstage.common.queryparam.ingredientstypemodelorcondition.IngredientCondition;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseBoxGroupMapper {


    /**
     *
     * 功能描述: 
     *
     * @param  countNameCondition
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:35
     */
    int selectCountByNameThisId(CountNameCondition countNameCondition);

    /**
     *
     * 功能描述: 
     *
     * @param  ingredientCondition
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:35
     */
    List<BaseBoxGroupEntry> selectByMachineTypeId(IngredientCondition ingredientCondition);

    /**
     *
     * 功能描述: 
     *
     * @param  name
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:35
     */
    List<BaseBoxGroupEntry> selectByName(String name);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:35
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:35
     */
    int insert(BaseBoxGroupEntry record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:35
     */
    int insertSelective(BaseBoxGroupEntry record);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:35
     */
    BaseBoxGroupEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:35
     */
    int updateByPrimaryKeySelective(BaseBoxGroupEntry record);

    
    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int updateByPrimaryKey(BaseBoxGroupEntry record);
}