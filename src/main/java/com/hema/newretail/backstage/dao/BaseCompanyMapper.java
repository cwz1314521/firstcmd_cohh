package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.BaseCompanyNameParameter;
import com.hema.newretail.backstage.common.queryparam.BaseCompanyNameQueryParameter;
import com.hema.newretail.backstage.common.queryparam.BaseCompanyQueryParameter;
import com.hema.newretail.backstage.common.queryparam.common.CompanyNameCondition;
import com.hema.newretail.backstage.common.queryparam.erp.ListManufacturerCondition;
import com.hema.newretail.backstage.entry.BaseCompanyData;
import com.hema.newretail.backstage.model.common.GridCompanyBo;
import com.hema.newretail.backstage.model.erp.ListManufacturerBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseCompanyMapper {


    /**
     *
     * 功能描述: 
     *
     * @param  listManufacturerCondition
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    List<ListManufacturerBo> selectBySelect(ListManufacturerCondition listManufacturerCondition);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    int insert(BaseCompanyData record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    int insertSelective(BaseCompanyData record);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    BaseCompanyData selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    int updateByPrimaryKeySelective(BaseCompanyData record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    int updateByPrimaryKey(BaseCompanyData record);

    /**
     *
     * 功能描述: 
     *
     * @param  
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    List<BaseCompanyData> selectAll();

    /**
     *
     * 功能描述: 
     *
     * @param  data
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    int updateStatusById(BaseCompanyData data);

    /**
     *
     * 功能描述: 
     *
     * @param  parameter
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    List<BaseCompanyData> findAll(BaseCompanyQueryParameter parameter);

    /**
     *
     * 功能描述: 
     *
     * @param  parameter
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    List<BaseCompanyData> findBaseCompanyByArea(BaseCompanyNameQueryParameter parameter);

    /**
     *
     * 功能描述: 
     *
     * @param  ids
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:38
     */
    List<BaseCompanyData> findCompanysByIds(@Param("ids") List<Long> ids);

    /**
     *
     * 功能描述: 
     *
     * @param  parameter
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:58
     */
    int countByCompanyName(BaseCompanyNameParameter parameter);



    /**
     *
     * 功能描述: 实时检索网格公司公共接口
     *
     * @param  condition
     * @return  List<GridCompanyBo>
     * @author  cwz
     * @date  2018/12/11 10:31
     */
    List<GridCompanyBo> selectCommon(CompanyNameCondition condition);
}