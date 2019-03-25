package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.erp.ListManufacturerCondition;
import com.hema.newretail.backstage.entry.BaseIngredientInfoEntry;
import com.hema.newretail.backstage.model.erp.ListManufacturerBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
@Component
public interface BaseIngredientInfoEntryMapper {



    /**
     *
     * 功能描述:
     *
     * @param    ingredientCode
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int selectCountByCode(String ingredientCode);

    /**
     *
     * 功能描述:
     *
     * @param    listManufacturerCondition
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    List<ListManufacturerBo> selectBySelect(ListManufacturerCondition listManufacturerCondition);

    /**
     *
     * 功能描述:
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    List<BaseIngredientInfoEntry> selectAll();

    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param    record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int insert(BaseIngredientInfoEntry record);

    /**
     *
     * 功能描述:
     *
     * @param    record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int insertSelective(BaseIngredientInfoEntry record);

    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    BaseIngredientInfoEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int updateByPrimaryKeySelective(BaseIngredientInfoEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int updateByPrimaryKey(BaseIngredientInfoEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   params
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    List<BaseIngredientInfoEntry> findAll(Map<String,Object> params);

    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int countByRefMenuIngredient(long id);

    /**
     *
     * 功能描述:
     *
     * @param   id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int countByBasePropertiesType(long id);
    /**
     *
     * 功能描述:
     *
     * @param   id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int countByBaseIngredientBox(@Param("id") Long id);

    /**
     *
     * 功能描述:
     *
     * @param   code
     * @return
     * @author  cwz
     * @date  2019/1/24 13:55
     */
    int verifyIngredientCode(@Param("code") String code);
}