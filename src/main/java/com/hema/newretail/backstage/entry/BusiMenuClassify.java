package com.hema.newretail.backstage.entry;

import com.hema.newretail.backstage.common.validator.First;
import com.hema.newretail.backstage.common.validator.Second;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhs
 */
public class BusiMenuClassify implements Serializable {
    private static final long serialVersionUID = 1851050271501568799L;
    @NotNull(message = "ID不能为空", groups = {Second.class})
    private Long id;

    @DecimalMin(value = "1", message = "排序字段最小为{value}", groups = {First.class})
    @DecimalMax(value = "999.99", message = "排序字段最大为{value}", groups = {First.class})
    private BigDecimal sort;

    @NotBlank(message = "分类名称不能为空", groups = {First.class})
    @Length(max = 8, message = "分类名称最大长度不超过{max}个字", groups = First.class)
    private String name;

    private String picUrl;

    @Min(value = 0, message = "状态必须为数字，最小值为{value}", groups = First.class)
    @Max(value = 1, message = "状态必须为数字，最大值为{value}", groups = First.class)
    private Integer status;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}