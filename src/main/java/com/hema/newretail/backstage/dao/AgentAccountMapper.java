package com.hema.newretail.backstage.dao;
/**
 *
 * 功能描述:
 *
 * @author  cwz
 * @date  2018/12/15 11:20
 */
import com.hema.newretail.backstage.entry.agent.AgentAccountEntry;


public interface AgentAccountMapper {
    /**
     *
     * 功能描述: 主键删除
     *
     * @param  id
     * @return  int
     * @author  cwz
     * @date  2018/12/15 11:13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return  int
     * @author  cwz
     * @date  2018/12/15 11:18
     */
    int insert(AgentAccountEntry record);
    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return  int
     * @author  cwz
     * @date  2018/12/15 11:18
     */
    int insertSelective(AgentAccountEntry record);

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return  AgentAccountEntry
     * @author  cwz
     * @date  2018/12/15 11:20
     */
    AgentAccountEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return  int
     * @author  cwz
     * @date  2018/12/15 11:20
     */
    int updateByPrimaryKeySelective(AgentAccountEntry record);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return  int
     * @author  cwz
     * @date  2018/12/15 11:20
     */
    int updateByPrimaryKey(AgentAccountEntry record);
}