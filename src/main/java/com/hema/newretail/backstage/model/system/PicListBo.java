package com.hema.newretail.backstage.model.system;
/**
 * @Department 新零售
 * @ClassName PicListBo
 * @Description listbo
 * @Author ---CWZ
 * @Date 2018/12/24 16:18
 * @Version 1.0
 **/


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PicListBo {

    private Long id;

    private String info;

    private String picUrl;

    private Integer status;

    private String gmtStart;

    private String gmtCreate;

    private String gmtEnd;

    private BigDecimal sort;

    private List<ListZnoeBo> list;
}
