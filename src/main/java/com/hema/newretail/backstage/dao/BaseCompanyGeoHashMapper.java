package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseCompanyGeoHashData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseCompanyGeoHashMapper {
    /**
     *
     * 功能描述: id
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:36
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:36
     */
    int insert(BaseCompanyGeoHashData record);
/**
 *
 * 功能描述: 
 *
 * @param  record
 * @return  
 * @author  cwz
 * @date  2019/1/24 13:36
 */
    int insertSelective(BaseCompanyGeoHashData record);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:36
     */
    BaseCompanyGeoHashData selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:36
     */
    int updateByPrimaryKeySelective(BaseCompanyGeoHashData record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:36
     */
    int updateByPrimaryKey(BaseCompanyGeoHashData record);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:36
     */
    int countByBaseCompanyId(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  baseCompanyId
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:36
     */
    List<BaseCompanyGeoHashData> findServiceAreaByBaseCompanyId(Long baseCompanyId);

    /**
     *
     * 功能描述: 
     *
     * @param  parameter
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    List<String> freeGeoHash(@Param("mapGeoHash") List<String> parameter);

    /**
     *
     * 功能描述: 
     *
     * @param  baseCompanyId
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    List<String> findBaseCompanyGeoHashByCompanyId(Long baseCompanyId);

    /**
     *
     * 功能描述: 
     *
     * @param  list
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    int insertBatchBaseCompanyGeoHash(@Param("list") List<BaseCompanyGeoHashData> list);

    /**
     *
     * 功能描述: 
     *
     * @param  companyId
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:37
     */
    int deleteByCompanyId(Long companyId);

    /**
     *
     * 功能描述: 
     *
     * @param  parameter
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:57
     */
    List<BaseCompanyGeoHashData> findGeoHashs(@Param("mapGeoHash") List<String> parameter);

}