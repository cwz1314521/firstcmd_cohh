package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.agent.AgentListCondition;
import com.hema.newretail.backstage.common.queryparam.common.CompanyNameCondition;
import com.hema.newretail.backstage.common.queryparam.financial.AgentCapitalCondition;
import com.hema.newretail.backstage.entry.agent.AgentUserEntry;
import com.hema.newretail.backstage.model.agent.AgentListBo;
import com.hema.newretail.backstage.model.common.AgentCompanyBo;
import com.hema.newretail.backstage.model.common.GridCompanyBo;

import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface AgentUserMapper {


    /**
     *
     * 功能描述: 
     *
     * @param  companyName
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:33
     */
    int selectCountByName(String companyName);


    /**
     *
     * 功能描述: 逻辑删除
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:33
     */
    int deleteById(Long id);

    /**
     *
     * 功能描述: 代理列表查询
     *
     * @param  agentListCondition
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:33
     */
    List<AgentListBo> selectAgentList(AgentListCondition agentListCondition);
    /**
     *
     * 功能描述: 查询全部信息
     *
     * @param  
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:34
     */
    List<AgentUserEntry> selectAll();


    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:34
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:34
     */
    int insert(AgentUserEntry record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:34
     */
    int insertSelective(AgentUserEntry record);

    /**
     *
     * 功能描述: 
     *
     * @param  id
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:34
     */
    AgentUserEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:34
     */
    int updateByPrimaryKeySelective(AgentUserEntry record);

    /**
     *
     * 功能描述: 
     *
     * @param  record
     * @return  
     * @author  cwz
     * @date  2019/1/24 13:53
     */
    int updateByPrimaryKey(AgentUserEntry record);


    /**
     *
     * 功能描述: 实时检索网格公司公共接口
     *
     * @param  condition
     * @return  List<GridCompanyBo>
     * @author  cwz
     * @date  2018/12/11 10:31
     */
    List<AgentCompanyBo> selectCommon(CompanyNameCondition condition);
    /**
     *
     * 功能描述: 
     *
     * @param  condition
     * @return  
     * @author  cwz
     * @date  2019/1/10 18:31
     */
    List<Map> selectAgentCapitalList(AgentCapitalCondition condition);

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return
     * @author  cwz
     * @date  2019/1/11 20:42
     */
    int updateClearing(AgentCapitalCondition condition);

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return
     * @author  cwz
     * @date  2019/1/11 20:42
     */
    int updateEdit(AgentCapitalCondition condition);
}