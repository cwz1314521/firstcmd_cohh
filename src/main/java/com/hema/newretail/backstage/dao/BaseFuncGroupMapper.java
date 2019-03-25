package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseFuncGroup;
import com.hema.newretail.backstage.entry.BaseFuncGroupIsSelectEntry;
import com.hema.newretail.backstage.model.authority.BaseFuncGroupMoudleBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseFuncGroupMapper {


    /**
     *
     * 功能描述:
     *
     * @param   postId
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    List<BaseFuncGroupIsSelectEntry> isSelectedAll(Long postId);

    /**
     *
     * 功能描述:
     *
     * @param   postId
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    List<BaseFuncGroupMoudleBo> selectBaseFuncGroupMoudleBo(@Param("postId") Long postId);

    /**
     *
     * 功能描述:
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    List<BaseFuncGroup> selectAll();

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    int insert(BaseFuncGroup record);

    /**
     *
     * 功能描述:
     *
     * @param    record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    int insertSelective(BaseFuncGroup record);

    /**
     *
     * 功能描述:
     *
     * @param   id
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    BaseFuncGroup selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    int updateByPrimaryKeySelective(BaseFuncGroup record);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return
     * @author  cwz
     * @date  2019/1/24 13:48
     */
    int updateByPrimaryKey(BaseFuncGroup record);
}