package com.hema.newretail.backstage.dao;
/**
 * @author cwz
 */
import com.hema.newretail.backstage.common.queryparam.system.MachinePicListCondition;
import com.hema.newretail.backstage.entry.BaseMachineScreenPicEntry;
import com.hema.newretail.backstage.model.system.PicListBo;

import java.util.List;

public interface BaseMachineScreenPicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseMachineScreenPicEntry record);

    int insertSelective(BaseMachineScreenPicEntry record);

    BaseMachineScreenPicEntry selectByPrimaryKey(Long id);


    List<PicListBo> selectListMap(MachinePicListCondition condition);

    int updateByPrimaryKeySelective(BaseMachineScreenPicEntry record);

    int updateByPrimaryKey(BaseMachineScreenPicEntry record);
}