package com.hema.newretail.backstage.model.diy;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName DetailEventBo
 * @Description detail
 * @Author ---CWZ
 * @Date 2018/10/18 12:52
 * @Version 1.0
 **/
@Data
public class DetailEventBo implements Serializable {

    private Long id ;

    private String ingredientName;

    private Long ingredientId;

    private BigDecimal maxIngredient;

    private Integer maxTime;

    private List<DetailEventSonBo> list;


    private String markedWords;


}
