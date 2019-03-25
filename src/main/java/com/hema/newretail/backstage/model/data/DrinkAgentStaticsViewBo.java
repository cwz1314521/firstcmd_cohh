package com.hema.newretail.backstage.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName DrinkAgentStaticsViewBo
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/12/19 19:17
 * @Version 1.0
 **/


@Data
public class DrinkAgentStaticsViewBo {
    /***
     * mongodb
     */
    @JsonIgnore
    private Object id;
    private String agentId;
    private String day;
    private String machineUuid;
    @JsonIgnore
    private String columnKey;
    private Integer cupNum;
    private Integer monthCupNum;
    private Integer totalCupNum;
    private Integer maxCupNum;
    private Integer averageCupNum;
    @JsonIgnore
    private Integer totalDays;
    /**
     * mysql
     * */
    private String machineName;
    private String agentName;


}
