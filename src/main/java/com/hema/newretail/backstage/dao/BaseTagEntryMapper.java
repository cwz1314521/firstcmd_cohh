package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.tag.TagCountTagNameCondition;
import com.hema.newretail.backstage.entry.BaseTagEntry;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseTagEntryMapper {


    int selectCountByTNameNotThisId(TagCountTagNameCondition tagCountTagNameCondition);

    int selectCountByTName(String tagname);

    List<BaseTagEntry> selectNotDelete();

    int deleteByPrimaryKey(Long id);

    int insert(BaseTagEntry record);

    int insertSelective(BaseTagEntry record);

    BaseTagEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseTagEntry record);

    int updateByPrimaryKey(BaseTagEntry record);
}