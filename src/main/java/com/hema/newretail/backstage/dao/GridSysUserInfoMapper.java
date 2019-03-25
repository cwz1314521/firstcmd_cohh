package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.grid.GridSysUserInfoEntry;
import org.apache.ibatis.annotations.Param;

public interface GridSysUserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grid_sysuser_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grid_sysuser_info
     *
     * @mbggenerated
     */
    int insert(GridSysUserInfoEntry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grid_sysuser_info
     *
     * @mbggenerated
     */
    int insertSelective(GridSysUserInfoEntry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grid_sysuser_info
     *
     * @mbggenerated
     */
    GridSysUserInfoEntry selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grid_sysuser_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GridSysUserInfoEntry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grid_sysuser_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GridSysUserInfoEntry record);


    int updateReset(@Param("companyId") Long companyId,@Param("password") String password);
}