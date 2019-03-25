package com.hema.newretail.backstage.model.device.sold;

import lombok.Data;

/**
 * @Department 新零售
 * @ClassName RedisSoldStatusBo
 * @Description redis  sold
 *   status
 * @Author ---CWZ
 * @Date 2018/12/14 10:03
 * @Version 1.0
 **/

@Data
public class RedisSoldStatusBo {

    private String machineState;
    private String stateDesc;
    private String duration;
}
