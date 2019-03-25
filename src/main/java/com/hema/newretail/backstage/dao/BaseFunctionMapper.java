package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseFunction;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseFunctionMapper {
    /**
     *
     * 功能描述:
     *
     * @param    funcCode
     * @return
     * @author  cwz
     * @date  2019/1/24 13:50
     */
    int deleteByPrimaryKey(String funcCode);

    /**
     *
     * 功能描述:
     *
     * @param     record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:50
     */
    int insert(BaseFunction record);

    /**
     *
     * 功能描述:
     *
     * @param    record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:50
     */
    int insertSelective(BaseFunction record);

    /**
     *
     * 功能描述:
     *
     * @param     funcCode
     * @return
     * @author  cwz
     * @date  2019/1/24 13:50
     */
    BaseFunction selectByPrimaryKey(String funcCode);

    /**
     *
     * 功能描述:
     *
     * @param    record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:50
     */
    int updateByPrimaryKeySelective(BaseFunction record);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:50
     */
    int updateByPrimaryKey(BaseFunction record);
}