package com.hema.newretail.backstage.service.impl;

import com.hema.newretail.backstage.dao.BaseIngredientBoxMapper;
import com.hema.newretail.backstage.dao.BoxGroupMapper;
import com.hema.newretail.backstage.dao.RefZoneMachineMapper;
import com.hema.newretail.backstage.dao.ZoneBaseMapper;
import com.hema.newretail.backstage.entry.RefZoneMachine;
import com.hema.newretail.backstage.entry.ZoneBase;
import com.hema.newretail.backstage.model.tag.BaseIngredientBoxInfoBo;
import com.hema.newretail.backstage.model.zonebase.BoxGroupBo;
import com.hema.newretail.backstage.model.zonebase.ZoneBo;
import com.hema.newretail.backstage.model.zonebase.ZoneMapGridBo;
import com.hema.newretail.backstage.service.IZoneBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * hema-newetaril-com.hema.newretail.backstage.service.impl
 *
 * @author ZhangHaiSheng
 * @date 2018-08-25 14:45
 */
@Service("zoneBaseService")
public class ZoneBaseServiceImpl implements IZoneBaseService {

    @Autowired
    private ZoneBaseMapper zoneBaseMapper;
    @Autowired
    private BoxGroupMapper boxGroupMapper;
    @Autowired
    private RefZoneMachineMapper refZoneMachineMapper;
    @Autowired
    private BaseIngredientBoxMapper baseIngredientBoxMapper;

    /**
     * @param paramsMap
     * @return
     */
    @Override
    public List<ZoneBo> queryZoneList(Map<String, Object> paramsMap) {
        return zoneBaseMapper.selectZoneListByCondition(paramsMap, (Integer) paramsMap.get("pageNum"), (Integer) paramsMap.get("pageSize"));
    }

    @Override
    public List<ZoneMapGridBo> queryZoneMap(Map<String, Object> paramsMap) {
        return zoneBaseMapper.selectZoneForMap(paramsMap);
    }

    @Override
    public List<String> getHashCode(List<Long> list) {
        return zoneBaseMapper.getHashCode(list);
    }

    @Override
    public List<String> getMachHashCode(Map<String, Object> paramsMap) {
        return zoneBaseMapper.getMachHashCode(paramsMap);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertZoneData(String zoneName, String machineTypeId, String province, String city, String area, String[] hashcodes) {
        Integer machineNum = 0;
        if (null != hashcodes && hashcodes.length > 0) {
            // 检测是否有交叉片区
            if (this.isHaveCrossZone(hashcodes, null)) {
                return -1L;
            }
            machineNum = zoneBaseMapper.selectMachineNumByHashcode(hashcodes);
        }
        ZoneBase zoneBase = new ZoneBase();
        zoneBase.setArea(area);
        zoneBase.setCity(city);
        zoneBase.setGmtCreate(new Date());
        zoneBase.setGmtModified(new Date());
        zoneBase.setMachineNum(machineNum);
        zoneBase.setMachineTypeId(Long.valueOf(machineTypeId));
        zoneBase.setProvince(province);
        zoneBase.setZoneName(zoneName);
        zoneBaseMapper.insertSelective(zoneBase);

        if (null != hashcodes && hashcodes.length > 0) {
            int num = refZoneMachineMapper.insertBatch(getZoneMachineList(hashcodes, zoneBase.getId()));
            // add by zhs at 20181120 for task to kafka
            if (num > 0) {
                return zoneBase.getId();
            }
        }
        return 0L;
    }

    @Override
    public List<BoxGroupBo> queryBoxGroupList(Long machineTypeId, Long zoneId, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("machineTypeId", machineTypeId);
        map.put("zoneId", zoneId);
        return boxGroupMapper.selectBoxGroup(map, pageNum, pageSize);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Map<String, Long>> updateBoxGroupId(String[] zoneId, String boxGroupId) {
        List<Map<String, Long>> list = new ArrayList<>(16);
        if (null != zoneId && zoneId.length > 0) {
            for (String id : zoneId) {
                ZoneBase zoneBase = zoneBaseMapper.selectByPrimaryKey(Long.valueOf(id));
                if (null == zoneBase) {
                    return null;
                }
                Long oldBoxGroupId = zoneBase.getBoxGroupId();
                zoneBase.setBoxGroupId(Long.valueOf(boxGroupId));
                int num = zoneBaseMapper.updateByPrimaryKeySelective(zoneBase);
                // add by zhs at 20181120 for task to kafka
                if (num > 0) {
                    if (!oldBoxGroupId.equals(Long.valueOf(boxGroupId))) {
                        Map<String, Long> map = new HashMap<>(3);
                        map.put("zoneId", zoneBase.getId());
                        map.put("oldGroupId", oldBoxGroupId);
                        map.put("newGroupId", Long.valueOf(boxGroupId));
                        list.add(map);
                    }
                }
            }
        }
        return list;
    }

    /**
     * @param zoneId
     * @return
     */
    @Override
    public ZoneBase queryOneByZoneId(Long zoneId) {
        return zoneBaseMapper.selectByPrimaryKey(zoneId);
    }

    /**
     * @param zoneId
     * @return
     */
    @Override
    public List<RefZoneMachine> queryGeoHashcodeByZoneId(Long zoneId) {
        return refZoneMachineMapper.selectByZoneid(zoneId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> updateZoneData(Long zoneId, String zoneName, String province, String city, String area, String[] hashcodes) {
        Map<String, Object> resutMap = new HashMap<>(2);
        ZoneBase zoneBase = this.queryOneByZoneId(zoneId);
        if (null == zoneBase) {
            resutMap.put("result", -1);
            return resutMap;
        }
        Integer machineNum = zoneBase.getMachineNum();

        if (null != hashcodes && hashcodes.length > 0) {
            // 检测是否有交叉片区
            if (this.isHaveCrossZone(hashcodes, zoneId)) {
                resutMap.put("result", -2);
                return resutMap;
            }
            // add by zhs at 20181120 for task to kafka
            List<String> geoHashsOld = refZoneMachineMapper.selectGeoHashByZoneid(zoneId);
            Collection<String> oldc = new ArrayList<>(geoHashsOld);
            Collection<String> newc = new ArrayList<>(Arrays.asList(hashcodes));
            newc.removeAll(oldc);
            // add by zhs at 20181120 for task to kafka
            refZoneMachineMapper.deleteByZoneid(zoneId);
            machineNum = zoneBaseMapper.selectMachineNumByHashcode(hashcodes);
            List<RefZoneMachine> list = getZoneMachineList(hashcodes, zoneId);
            if (null != list) {
                int num = refZoneMachineMapper.insertBatch(list);
                // add by zhs at 20181120 for task to kafka
                if (num > 0 && newc.size() > 0) {
                    String[] hashs = new String[newc.size()];
                    newc.toArray(hashs);
                    resutMap.put("newHashs", hashs);
                }
                // add by zhs at 20181120 for task to kafka
            }
        } else {
            refZoneMachineMapper.deleteByZoneid(zoneId);
        }
        zoneBase.setArea(area);
        zoneBase.setCity(city);
        zoneBase.setGmtModified(new Date());
        zoneBase.setMachineNum(machineNum);
        zoneBase.setProvince(province);
        zoneBase.setZoneName(zoneName);
        int num = zoneBaseMapper.updateByPrimaryKeySelective(zoneBase);
        resutMap.put("result", num);
        return resutMap;
    }

    /**
     * 一键查询所有网格
     *
     * @param province
     * @param city
     * @param area
     * @return
     */
    @Override
    public List<RefZoneMachine> queryAllGruidByArea(String province, String city, String area) {
        Map<String, Object> paramsMap = new HashMap<>(3);
        paramsMap.put("province", province);
        paramsMap.put("city", city);
        paramsMap.put("area", area);
        return refZoneMachineMapper.selectAllGruidByArea(paramsMap);
    }

    /**
     * 查询给定网格的所有设备数
     *
     * @param hashcodes 网格
     * @return 设备数
     */
    @Override
    public Integer queryMachineNumByGeoHash(String[] hashcodes) {
        Integer machineNum = 0;
        if (null != hashcodes && hashcodes.length > 0) {
            machineNum = zoneBaseMapper.selectMachineNumByHashcode(hashcodes);
        }
        return machineNum;
    }

    /**
     * 检测是否有交叉片区
     *
     * @return true 有 false 没有
     */
    private Boolean isHaveCrossZone(String[] hashcodes, Long zoneId) {
        List<String> hashCodeList = zoneBaseMapper.getHashCodeCross(hashcodes, zoneId);
        return null != hashCodeList && hashCodeList.size() > 0;
    }

    /**
     * @param hashcodes
     * @param zoneId
     * @return
     */
    private List<RefZoneMachine> getZoneMachineList(String[] hashcodes, Long zoneId) {
        List<RefZoneMachine> refZoneMachineList = new ArrayList<>();
        for (String geoHashcode : hashcodes) {
            RefZoneMachine refZoneMachine = new RefZoneMachine();
            refZoneMachine.setGeoHash(geoHashcode);
            refZoneMachine.setZoneId(zoneId);
            refZoneMachineList.add(refZoneMachine);
        }
        return refZoneMachineList;
    }

    @Override
    public List<BaseIngredientBoxInfoBo> ingredientInfo(Long boxGroupId) {
        return baseIngredientBoxMapper.selectBoxOrInfoByBoxGroupId(boxGroupId);
    }

}
