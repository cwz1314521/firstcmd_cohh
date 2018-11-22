package com.hema.newretail.backstage.common.utils.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hema.newretail.backstage.model.taskkafka.AddZoneHashBo;
import com.hema.newretail.backstage.model.taskkafka.IngredientBoxBo;
import com.hema.newretail.backstage.model.taskkafka.ModifyBoxGroupBo;
import com.hema.newretail.backstage.model.taskkafka.ModifyZoneBoxGroupBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private static String RELOAD_KAFKA_TOPIC = "reloadTopic";
    /**
     * 周期性任务修改周期时间topic
     */
    private static String CYCLE_KAFKA_TOPIC = "cycleTopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void addZoneHash(Long zoneId, String[] hashcodes) {
        AddZoneHashBo bo = new AddZoneHashBo();
        bo.setZoneId(zoneId);
        bo.setGeoHash(hashcodes);
        bo.setTimestamp(System.currentTimeMillis());
        kafkaTemplate.send(RELOAD_KAFKA_TOPIC, JSON.toJSONString(TaskResponse.initAddZoneHash(bo)));
    }

    public void modifyZoneBoxGroup(Long zoneId, Long oldGroupId, Long newGroupId) {
        ModifyZoneBoxGroupBo bo = new ModifyZoneBoxGroupBo();
        bo.setZoneId(zoneId);
        bo.setOldGroupId(oldGroupId);
        bo.setNewGroupId(newGroupId);
        bo.setTimestamp(System.currentTimeMillis());
        kafkaTemplate.send(RELOAD_KAFKA_TOPIC, JSON.toJSONString(TaskResponse.initModifyZoneBoxGroup(bo), SerializerFeature.WriteMapNullValue));
    }

    public void modifyBoxGroup(Long groupId, List<IngredientBoxBo> list) {
        if (list.isEmpty()) {
            return;
        }
        ModifyBoxGroupBo bo = new ModifyBoxGroupBo();
        bo.setGroupId(groupId);
        bo.setIngredientBox(list);
        bo.setTimestamp(System.currentTimeMillis());
        kafkaTemplate.send(RELOAD_KAFKA_TOPIC, JSON.toJSONString(TaskResponse.initModifyBoxGroup(bo), SerializerFeature.WriteMapNullValue));
    }
}
