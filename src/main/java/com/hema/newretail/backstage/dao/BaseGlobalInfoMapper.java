package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseGlobalInfoEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseGlobalInfoMapper {


/**
 *
 * 功能描述: 
 *
 * @param  list
 * @return  
 * @author  cwz
 * @date  2019/3/14 19:36
 */
    String selectByErrorCode(@Param("list") List<String> list);
    /**
     *
     * 功能描述:
     *
     * @param    key
     * @return
     * @author  cwz
     * @date  2019/1/24 13:52
     */
    BaseGlobalInfoEntry selectByKey(String key);

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:52
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:52
     */
    int insert(BaseGlobalInfoEntry record);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:52
     */
    int insertSelective(BaseGlobalInfoEntry record);

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:52
     */
    BaseGlobalInfoEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:52
     */
    int updateByPrimaryKeySelective(BaseGlobalInfoEntry record);

    /**
     *
     * 功能描述:
     *
     * @param    record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:52
     */
    int updateByPrimaryKey(BaseGlobalInfoEntry record);
}