package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.common.CompanyNameCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.AddressEditCondition;
import com.hema.newretail.backstage.common.queryparam.financial.MachineCashListCondition;
import com.hema.newretail.backstage.common.queryparam.financial.RetiredCashCondition;
import com.hema.newretail.backstage.entry.BaseMachineInfoEntry;
import com.hema.newretail.backstage.model.common.GridCompanyBo;
import com.hema.newretail.backstage.model.common.MapGeohashTagBo;
import com.hema.newretail.backstage.model.financial.CashListBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public interface BaseMachineInfoMapper {

    /**
     *
     * 功能描述: 
     *
     * @param  machineUuid
     * @return
     * @author  cwz
     * @date  2019/3/14 15:33
     */
    Map selectOrderByUuid(@Param("machineUuid")String machineUuid);


    /**
     * 功能描述:
     *
     * @param
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    List<BaseMachineInfoEntry> selectAll();

    /**
     * 功能描述:
     *
     * @param uuid
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    String selectByUUId(String uuid);

    /**
     * 功能描述:
     *
     * @param machineUuid
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    BaseMachineInfoEntry selectByUId(String machineUuid);

    /**
     * 功能描述:
     *
     * @param id
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 功能描述:
     *
     * @param record
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    int insert(BaseMachineInfoEntry record);

    /**
     * 功能描述:
     *
     * @param record
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    int insertSelective(BaseMachineInfoEntry record);

    /**
     * 功能描述:
     *
     * @param id
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    BaseMachineInfoEntry selectByPrimaryKey(Long id);

    /**
     * 功能描述:
     *
     * @param record
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    int updateByPrimaryKeySelective(BaseMachineInfoEntry record);

    /**
     * 功能描述:
     *
     * @param record
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    int updateByPrimaryKeyWithBLOBs(BaseMachineInfoEntry record);

    /**
     * 功能描述:
     *
     * @param record
     * @return
     * @author cwz
     * @date 2019/1/24 13:59
     */
    int updateByPrimaryKey(BaseMachineInfoEntry record);


    /**
     * 功能描述: 实时检索网格公司公共接口
     *
     * @param condition
     * @return List<GridCompanyBo>
     * @author cwz
     * @date 2018/12/11 10:31
     */
    List<GridCompanyBo> selectCommon(CompanyNameCondition condition);


    /**
     * 功能描述: 设备押金管理list
     *
     * @param condition
     * @return List<Map>
     * @author cwz
     * @date 2019/1/8 11:49
     */
    List<CashListBo> selectCashList(MachineCashListCondition condition);

    /**
     * 功能描述: 设备押金管理list 图标上方统计数据
     *
     * @param
     * @return List<Map>
     * @author cwz
     * @date 2019/1/8 13:53
     */
    List<Map> selectCashAllList();

    /**
     * 功能描述: 设置补贴规则
     *
     * @param condition
     * @return int
     * @author cwz
     * @date 2019/1/8 18:17
     */
    int updateRule(RetiredCashCondition condition);


    /**
     * 功能描述:
     *
     * @param condition
     * @return
     * @author cwz
     * @date 2019/1/24 17:01
     */
    int updateEditAddress(AddressEditCondition condition);

    /**
     * 根据id查询设备信息
     *
     * @param idList
     * @return
     */
    List<Map<String, Object>> selectById(@Param("idList") List<Long> idList);

    /**
     * 查询地图可视范围内的所有设备坐标
     *
     * @param parameter
     * @return
     */
    List<MapGeohashTagBo> queryAllTag(@Param("mapGeoHash") List<String> parameter);
}