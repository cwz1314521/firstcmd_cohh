package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseIngredientBoxEntry;
import com.hema.newretail.backstage.model.tag.BaseIngredientBoxInfoBo;
import com.hema.newretail.backstage.model.taskkafka.IngredientBoxBo;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseIngredientBoxMapper {

    /**
     *
     * 功能描述: 
     *
     * @param   boxGroupId
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:53
     */
    List<BaseIngredientBoxInfoBo> selectBoxOrInfoByBoxGroupId(Long boxGroupId);

    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:54
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:54
     */
    int deleteByGroupId(Long id);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:54
     */
    int insert(BaseIngredientBoxEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:54
     */
    int insertSelective(BaseIngredientBoxEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:54
     */
    BaseIngredientBoxEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:54
     */
    int updateByPrimaryKeySelective(BaseIngredientBoxEntry record);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:54
     */
    int updateByPrimaryKey(BaseIngredientBoxEntry record);

    /**
     * 查询配料方案的料盒信息给task用
     *
     * @param boxGroupId 配料方案ID
     * @return list
     */
    List<IngredientBoxBo> selectByBoxGroupIdForTask(Long boxGroupId);
}