package com.hema.newretail.backstage.entry.discounts;

import java.math.BigDecimal;
import java.util.Date;

public class BusiCouponEntry {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Long couponAuthId;

    private String couponName;

    private String couponTitle;

    private String couponNo;

    private String couponDescription;

    private String couponPic;

    private Integer couponType;

    private Integer period;

    private Integer periodUnit;

    private Integer limitnum;

    private Date dueTime;

    private Integer receiveStatus;

    private String receivePerson;

    private Date receiveTime;

    private String usePerson;

    private Integer useStatus;

    private Date useTime;

    private BigDecimal denomination;

    private String denominationUnit;

    private Long menuId;

    private String menuName;

    private String compensationNo;

    private Integer couponSource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getCouponAuthId() {
        return couponAuthId;
    }

    public void setCouponAuthId(Long couponAuthId) {
        this.couponAuthId = couponAuthId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle == null ? null : couponTitle.trim();
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription == null ? null : couponDescription.trim();
    }

    public String getCouponPic() {
        return couponPic;
    }

    public void setCouponPic(String couponPic) {
        this.couponPic = couponPic == null ? null : couponPic.trim();
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(Integer periodUnit) {
        this.periodUnit = periodUnit;
    }

    public Integer getLimitnum() {
        return limitnum;
    }

    public void setLimitnum(Integer limitnum) {
        this.limitnum = limitnum;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getReceivePerson() {
        return receivePerson;
    }

    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson == null ? null : receivePerson.trim();
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getUsePerson() {
        return usePerson;
    }

    public void setUsePerson(String usePerson) {
        this.usePerson = usePerson == null ? null : usePerson.trim();
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public String getDenominationUnit() {
        return denominationUnit;
    }

    public void setDenominationUnit(String denominationUnit) {
        this.denominationUnit = denominationUnit == null ? null : denominationUnit.trim();
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getCompensationNo() {
        return compensationNo;
    }

    public void setCompensationNo(String compensationNo) {
        this.compensationNo = compensationNo == null ? null : compensationNo.trim();
    }

    public Integer getCouponSource() {
        return couponSource;
    }

    public void setCouponSource(Integer couponSource) {
        this.couponSource = couponSource;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}