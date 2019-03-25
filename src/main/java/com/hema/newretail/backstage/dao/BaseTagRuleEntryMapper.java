package com.hema.newretail.backstage.dao;


import com.hema.newretail.backstage.common.queryparam.authority.TagCondition;
import com.hema.newretail.backstage.entry.BaseTagRuleEntry;
import com.hema.newretail.backstage.model.menu.TagMenuBo;

import org.springframework.stereotype.Component;


import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
@Component
public interface BaseTagRuleEntryMapper {

    int selectByMenuNameCount(TagCondition tagCondition);

    List<BaseTagRuleEntry> selectByTId(Long tagId);

    int deleteByPrimaryKey(Long id);

    int deleteByTId(Long tagId);

    int deleteByMenuId(Long menuId);

    int insert(BaseTagRuleEntry record);

    int insertSelective(BaseTagRuleEntry record);

    BaseTagRuleEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseTagRuleEntry record);

    int updateByPrimaryKey(BaseTagRuleEntry record);

    /**
     * @param menuId
     * @return
     */
    List<TagMenuBo> selectDataByMenuid(Long menuId);

    /**
     * 更改标签的量级
     *
     * @param record
     * @return
     */
    int updateByTagidAndMenuid(BaseTagRuleEntry record);
}