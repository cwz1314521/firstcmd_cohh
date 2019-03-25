package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.agent.AgentCooperationMode;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface AgentCooperationModeMapper {



    /**
     *
     * 功能描述: 查询代理公司有几条分成记录
     *
     * @param  agentUserId
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:32
     */
    List<AgentCooperationMode> selectByAgentUserId(Long agentUserId);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:32
     */
    int deleteByPrimaryKey(Long id);
    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:32
     */
    int insert(AgentCooperationMode record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:33
     */
    int insertSelective(AgentCooperationMode record);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:33
     */
    AgentCooperationMode selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:33
     */
    int updateByPrimaryKeySelective(AgentCooperationMode record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:52
     */
    int updateByPrimaryKey(AgentCooperationMode record);
}