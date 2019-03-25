package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseMachineBoxLogEntry;
import com.hema.newretail.backstage.model.grid.BoxLogBo;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseMachineBoxLogMapper {


    /**
     *
     * 功能描述:
     *
     * @param    version
     * @return
     * @author  cwz
     * @date  2019/1/24 13:57
     */
    List<BoxLogBo> selectByCreate(Long version);

    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:57
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param     record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:57
     */
    int insert(BaseMachineBoxLogEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:57
     */
    int insertSelective(BaseMachineBoxLogEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:57
     */
    BaseMachineBoxLogEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param    record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:57
     */
    int updateByPrimaryKeySelective(BaseMachineBoxLogEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:57
     */
    int updateByPrimaryKey(BaseMachineBoxLogEntry record);
}