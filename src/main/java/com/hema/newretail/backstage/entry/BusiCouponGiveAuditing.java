package com.hema.newretail.backstage.entry;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 */
public class BusiCouponGiveAuditing implements Serializable {
    private static final long serialVersionUID = -1738389315723534296L;
    private Long id;

    private Long couponAuditingId;

    private Integer couponGiveNumber;

    private String couponGiveRemark;

    private Date gmtCreate;

    private Date gmtModify;

    private Date applyTime;

    private Long applicantId;

    private String applicant;

    private Date auditTime;

    private Long auditorId;

    private String auditor;

    private Integer auditStatus;

    private String auditDesc;

    private String givePersonId;
	/**
	 * 用户名--手机号
	 */
	private List<String> phoneList;
	/**
	 * 优惠券名称
	 */
	private String couponName;

	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponAuditingId() {
        return couponAuditingId;
    }

    public void setCouponAuditingId(Long couponAuditingId) {
        this.couponAuditingId = couponAuditingId;
    }

    public Integer getCouponGiveNumber() {
        return couponGiveNumber;
    }

    public void setCouponGiveNumber(Integer couponGiveNumber) {
        this.couponGiveNumber = couponGiveNumber;
    }

    public String getCouponGiveRemark() {
        return couponGiveRemark;
    }

    public void setCouponGiveRemark(String couponGiveRemark) {
        this.couponGiveRemark = couponGiveRemark == null ? null : couponGiveRemark.trim();
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

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc == null ? null : auditDesc.trim();
    }

    public String getGivePersonId() {
        return givePersonId;
    }

    public void setGivePersonId(String givePersonId) {
        this.givePersonId = givePersonId == null ? null : givePersonId.trim();
    }
}