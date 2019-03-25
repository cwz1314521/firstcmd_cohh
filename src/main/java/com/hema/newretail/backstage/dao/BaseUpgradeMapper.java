package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseUpgrade;

import java.util.List;

public interface BaseUpgradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseUpgrade record);

    int insertSelective(BaseUpgrade record);

    BaseUpgrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseUpgrade record);

    int updateByPrimaryKey(BaseUpgrade record);

	/**
	 * 获取APP升级信息
	 * @param condition
	 * @return
	 */
	List<BaseUpgrade> baseUpgradeList(BaseUpgrade condition);
}