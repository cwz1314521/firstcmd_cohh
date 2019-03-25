package com.hema.newretail.backstage.entry;
/**
 *
 * 功能描述:
 *
 * @param
 * @return
 * @author  cwz
 * @date  2019/2/26 10:47
 */
import lombok.Data;

import java.util.Date;
@Data
public class BaseIndexArticleEntry {
    private Long id;

    private String picUrl;

    private String title;

    private Date gmtCreate;

    private Date gmtModify;

    private Date gmtStart;

    private Date gmtEnd;

    private String skipUrl;

    private Double sort;

    private Long type;

    private String typeName;

    private Integer status;

    private String articleAbstract;

    private String isDelete;

    private String info;
}