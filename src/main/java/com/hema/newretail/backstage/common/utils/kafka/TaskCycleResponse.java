package com.hema.newretail.backstage.common.utils.kafka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.utils.kafka
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-14 11:16
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskCycleResponse {
    /**
     * 任务类型：2换件、3保洁、5巡检
     */
    private Integer taskType;

    /**
     * 设备的UUID
     */
    private String machineUuid;

    /**
     * 任务周期，以天为单位
     */
    private Integer period;

    /**
     * 任务周期，以天为单位
     */
    private Integer oldPeriod;
    /**
     * 来源 0:机器  1:运营后台 2:网格后台
     */
    private Integer assigner;

    /**
     * 设置修改的任务周期
     *
     * @param uuid      设备的UUID
     * @param period    更新之后的任务周期，以天为单位
     * @param oldPeriod 更新之前的任务周期，以天为单位
     * @param taskType  任务类型：2换件、3保洁、5巡检
     * @return r
     */
    public static TaskCycleResponse initTaskCycle(String uuid, Integer period, Integer oldPeriod, Integer taskType) {
        TaskCycleResponse taskResponse = new TaskCycleResponse();
        taskResponse.setAssigner(1);
        taskResponse.setMachineUuid(uuid);
        taskResponse.setPeriod(period);
        taskResponse.setTaskType(taskType);
        taskResponse.setOldPeriod(oldPeriod);
        return taskResponse;
    }
}
