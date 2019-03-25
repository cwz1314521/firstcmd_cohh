package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.user.BusiComplaintListCondition;
import com.hema.newretail.backstage.entry.user.BusiComplaintEntry;
import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 
 *
 * @param  
 * @return
 * @author  cwz
 * @date  2019/3/23 11:02
 */
public interface BusiComplaintMapper {

    List<Map> list(BusiComplaintListCondition condition);

    int remark(BusiComplaintListCondition condition);

    int deleteByPrimaryKey(Long id);

    int insert(BusiComplaintEntry record);

    int insertSelective(BusiComplaintEntry record);

    BusiComplaintEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiComplaintEntry record);

    int updateByPrimaryKeyWithBLOBs(BusiComplaintEntry record);

    int updateByPrimaryKey(BusiComplaintEntry record);
}