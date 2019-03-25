package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BasePushMessageEntry;
import com.hema.newretail.backstage.model.agent.PushListBo;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BasePushMessageMapper {




    List<PushListBo> selectByAgent();

    int insertBatch(List<BasePushMessageEntry> list);
    
}