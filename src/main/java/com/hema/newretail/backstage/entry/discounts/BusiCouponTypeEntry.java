package com.hema.newretail.backstage.entry.discounts;
/**
 * @author
 */

import lombok.Data;

import java.util.Date;
@Data
public class BusiCouponTypeEntry {
    private Long id;

    private String typeName;

    private Date gmtCreate;

    private Date gmtModified;
}