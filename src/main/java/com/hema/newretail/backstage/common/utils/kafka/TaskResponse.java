package com.hema.newretail.backstage.common.utils.kafka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.utils.kafka
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-21 10:49
 */
@Setter
@Getter
@NoArgsConstructor
public class TaskResponse {
    /**
     * 0 片区网格新增或新增片区 1片区方案改变  2 方案调整
     */
    private Integer operationType;
    private Object reloadDetail;
    /**
     * 来源 0:机器  1:运营后台 2:网格后台
     */
    private Integer assigner;

    /**
     * 新增片区网格
     *
     * @param data data
     * @return r
     */
    public static TaskResponse initAddZoneHash(Object data) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setOperationType(0);
        taskResponse.setAssigner(1);
        taskResponse.setReloadDetail(data);
        return taskResponse;
    }

    /**
     * 修改片区方案
     *
     * @param data data
     * @return r
     */
    public static TaskResponse initModifyZoneBoxGroup(Object data) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setOperationType(1);
        taskResponse.setAssigner(1);
        taskResponse.setReloadDetail(data);
        return taskResponse;
    }

    /**
     * 方案调整
     *
     * @param data data
     * @return r
     */
    public static TaskResponse initModifyBoxGroup(Object data) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setOperationType(2);
        taskResponse.setAssigner(1);
        taskResponse.setReloadDetail(data);
        return taskResponse;
    }
}
