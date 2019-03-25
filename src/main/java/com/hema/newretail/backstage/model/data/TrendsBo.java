package com.hema.newretail.backstage.model.data;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName TrendsBo
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/12/18 13:01
 * @Version 1.0
 **/



@Data
public class TrendsBo {

    private List<String> xdate;
    private List<Integer> xCupNumber;
    private List<BigDecimal> xAveragePrice;
    private Integer maxCup;
    private BigDecimal maxAveragePrice;
}
