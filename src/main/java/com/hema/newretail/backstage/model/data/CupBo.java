package com.hema.newretail.backstage.model.data;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName CupBo
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/12/21 16:32
 * @Version 1.0
 **/


@Data
public class CupBo {
    private Integer ZCup;
    private Integer MaxDayCup;
    private BigDecimal ADayCup;
    private List<DrinkAgentStaticsViewBo> list;
}
