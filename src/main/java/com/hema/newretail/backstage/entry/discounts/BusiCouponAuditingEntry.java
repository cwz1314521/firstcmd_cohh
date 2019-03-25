package com.hema.newretail.backstage.entry.discounts;
/**
 * @author
 */

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class BusiCouponAuditingEntry {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String couponName;

    private String couponTitle;

    private String couponDescription;

    private String couponPic;

    private Integer couponType;

    private Integer period;

    private Integer periodUnit;

    private Integer authStatus;

    private String authDesc;

    private Integer limitnum;

    private Integer isDeleted;

    private Integer num;

    private Integer surplusNum;

    private BigDecimal denomination;

    private String denominationUnit;

    private List<Map> mapList;
}