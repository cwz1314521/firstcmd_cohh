package com.hema.newretail.backstage.entry.discounts;
/**
 * @author
 */
import lombok.Data;

@Data
public class RefMenuCouponEntry {
    private Long id;

    private Long menuId;

    private Long couponId;
}