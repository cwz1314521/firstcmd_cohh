package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.RefPostFuncGroup;
import org.apache.ibatis.annotations.Param;

public interface RefPostFuncGroupMapper {

    int deleteByPId(@Param("postId") Long postId);

    int insert(RefPostFuncGroup record);

    int insertSelective(RefPostFuncGroup record);
}