package com.hema.newretail.backstage.dao;

/**
 *
 * 功能描述: SetupUserMapper
 *
 * @author: cwz
 * @date: 2018/12/10 10:03
 */
import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonListDBCondition;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.UsernameCondition;
import com.hema.newretail.backstage.entry.SetupUserEntry;
import java.util.List;

public interface SetupUserMapper {
    /**
     *
     * 功能描述: 主键删除
     *
     * @param  id
     * @return int
     * @author: cwz
     * @date: 2018/12/10 10:00
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 新增
     *
     * @param  record
     * @return  int
     * @author cwz
     * @date 2018/12/10 10:00
     */
    int insert(SetupUserEntry record);

    /**
     *
     * 功能描述: 新增Selective
     * @param  record
     * @return int
     * @author cwz
     * @date 2018/12/10 10:00
     */
    int insertSelective(SetupUserEntry record);

    /**
     *
     * 功能描述: 主键查询
     * @param  id
     * @return  SetupUserEntry
     * @author cwz
     * @date 2018/12/10 10:00
     */

    SetupUserEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述: 安装人员列表
     *
     * @param condition
     * @return List<SetupUserEntry>
     * @author cwz
     * @date 2018/12/7 16:31
     */
    List<SetupUserEntry> list(InstallPersonListDBCondition condition);

    /**
     *
     * 功能描述: 检索账号是否重复sql
     *
     * @param usernameCondition
     * @return int
     * @author cwz
     * @date 2018/12/7 16:31
     */
    int countMobile(UsernameCondition usernameCondition);

    /**
     *
     * 功能描述: 安装人员更新操作
     *
     * @param record
     * @return int
     * @author cwz
     * @date 2018/12/8 10:17
     */
    int update(SetupUserEntry record);

    /**
     *
     * 功能描述: 安装人员逻辑删除操作
     *
     * @param record
     * @return int
     * @author cwz
     * @date 2018/12/8 10:17
     */
    int delete(SetupUserEntry record);

    /**
     *
     * 功能描述: 安装人员重置密码操作
     *
     * @param record
     * @return int
     * @author cwz
     * @date 2018/12/8 10:17
     */
    int reset(SetupUserEntry record);
}