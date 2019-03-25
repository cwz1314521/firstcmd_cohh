package com.hema.newretail.backstage.common.utils.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hema.newretail.backstage.common.queryparam.device.sold.SetTaskCycleCondition;
import com.hema.newretail.backstage.model.taskkafka.AddZoneHashBo;
import com.hema.newretail.backstage.model.taskkafka.IngredientBoxBo;
import com.hema.newretail.backstage.model.taskkafka.ModifyBoxGroupBo;
import com.hema.newretail.backstage.model.taskkafka.ModifyZoneBoxGroupBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.utils.kafka
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-20 17:20
 */
@Component
public class TaskKafkaHelper {
    /**
     * 换料任务topic
     */
    @Value("${kafka.topic.reload}")
    private String reloadKafkaTopic;
    /**
     * 周期性任务修改周期时间topic
     */
    @Value("${kafka.topic.cycle}")
    private String cycleKafkaTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 新增片区网格时，发送通知
     *
     * @param zoneId    片区ID
     * @param hashcodes 新增的网格hashcode
     */
    public void addZoneHash(Long zoneId, String[] hashcodes) {
        AddZoneHashBo bo = new AddZoneHashBo();
        bo.setZoneId(zoneId);
        bo.setGeoHash(hashcodes);
        bo.setTimestamp(System.currentTimeMillis() / 1000);
        kafkaTemplate.send(reloadKafkaTopic, JSON.toJSONString(TaskResponse.initAddZoneHash(bo)));
    }

    /**
     * 修改片区的配料方案时，发送通知
     *
     * @param list 集合参数
     */
    public void modifyZoneBoxGroup(List<Map<String, Long>> list) {
        for (Map<String, Long> map : list) {
            Long id = map.get("zoneId");
            Long oldGroupId = map.get("oldGroupId");
            Long newGroupId = map.get("newGroupId");
            modifyZoneBoxGroup(id, oldGroupId, newGroupId);
        }
    }

    /**
     * 修改片区的配料方案时，发送通知
     *
     * @param zoneId     片区ID
     * @param oldGroupId 旧的配料方案ID
     * @param newGroupId 新的配料方案ID
     */
    public void modifyZoneBoxGroup(Long zoneId, Long oldGroupId, Long newGroupId) {
        ModifyZoneBoxGroupBo bo = new ModifyZoneBoxGroupBo();
        bo.setZoneId(zoneId);
        bo.setOldGroupId(oldGroupId);
        bo.setNewGroupId(newGroupId);
        bo.setTimestamp(System.currentTimeMillis() / 1000);
        System.out.println("start kafka");
        kafkaTemplate.send(reloadKafkaTopic, JSON.toJSONString(TaskResponse.initModifyZoneBoxGroup(bo), SerializerFeature.WriteMapNullValue));
        System.out.println("end kafka");
    }

    /**
     * 修改配料方案时，发送通知
     *
     * @param groupId 配料方案ID
     * @param list    修改的配料方案数据
     */
    public void modifyBoxGroup(Long groupId, List<IngredientBoxBo> list) {
        if (list.isEmpty()) {
            return;
        }
        ModifyBoxGroupBo bo = new ModifyBoxGroupBo();
        bo.setGroupId(groupId);
        bo.setIngredientBox(list);
        bo.setTimestamp(System.currentTimeMillis() / 1000);
        kafkaTemplate.send(reloadKafkaTopic, JSON.toJSONString(TaskResponse.initModifyBoxGroup(bo), SerializerFeature.WriteMapNullValue));
    }

    /**
     * 修改任务设置周期时，发送通知
     *
     * @param list      新的数据
     * @param condition 旧的数据
     */
    public void modifyTaskCycle(List<Map<String, Object>> list, SetTaskCycleCondition condition) {
        for (Map<String, Object> map : list) {
            String uuid = (String) map.get("machineUuid");
            Integer cleanupCycle = 0;
            if (map.get("cleanupCycle") != null) {
                cleanupCycle = ((Long) map.get("cleanupCycle")).intValue();
            }
            Integer replaceCycle = 0;
            if (map.get("replaceCycle") != null) {
                replaceCycle = ((Long) map.get("replaceCycle")).intValue();
            }
            Integer inspectionCycle = 0;
            if (map.get("inspectionCycle") != null) {
                inspectionCycle = ((Long) map.get("inspectionCycle")).intValue();
            }
            System.out.println("start kafka::cycle");
            if (!cleanupCycle.equals(condition.getCleanupCycle())) {
                kafkaTemplate.send(cycleKafkaTopic, JSON.toJSONString(TaskCycleResponse.initTaskCycle(uuid, condition.getCleanupCycle(), cleanupCycle, TaskTypeEnum.CLEAN_UP.ordinal()), SerializerFeature.WriteMapNullValue));
            }
            if (!replaceCycle.equals(condition.getReplaceCycle())) {
                kafkaTemplate.send(cycleKafkaTopic, JSON.toJSONString(TaskCycleResponse.initTaskCycle(uuid, condition.getReplaceCycle(), replaceCycle, TaskTypeEnum.REPLACE.ordinal()), SerializerFeature.WriteMapNullValue));
            }
            if (!inspectionCycle.equals(condition.getInspectionCycle())) {
                kafkaTemplate.send(cycleKafkaTopic, JSON.toJSONString(TaskCycleResponse.initTaskCycle(uuid, condition.getInspectionCycle(), inspectionCycle, TaskTypeEnum.INSPECTION.ordinal()), SerializerFeature.WriteMapNullValue));
            }
            System.out.println("end kafka::cycle");
        }
    }
}
