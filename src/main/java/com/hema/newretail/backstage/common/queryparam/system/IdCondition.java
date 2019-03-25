package com.hema.newretail.backstage.common.queryparam.system;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName IdCondition
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/12/24 16:59
 * @Version 1.0
 **/


@Data
public class IdCondition {

    @NotNull(message = "id不可为空")
    private Long id;
}
