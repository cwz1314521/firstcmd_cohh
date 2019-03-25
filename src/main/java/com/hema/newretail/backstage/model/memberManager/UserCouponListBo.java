package com.hema.newretail.backstage.model.memberManager;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.memberManager
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-16 14:35
 */
@Data
public class UserCouponListBo implements Serializable {
    private static final long serialVersionUID = -8701768001122307029L;
    private String couponName;
    private Integer couponType;
    private BigDecimal denomination;
    private String denominationUnit;
    private String couponNo;
    private Date receiveTime;
    private Date dueTime;
    private String menus;
}
