package com.hema.newretail.backstage.entry.discounts;
/**
 * @author
 */
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class BusiActivityEntry implements Serializable {
    private static final long serialVersionUID = -3373275218164611489L;

    private Long id;

    private String activityName;

    private String subTitle;

    private Date effectiveTime;

    private Date invalidTime;

    private String activityDesc;

    private Integer activityStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private Integer time;

    private Long activityRuleId;

    private Integer peopleNumber;

    private BigDecimal subsidy;

    private BigDecimal activitySubsidyLimit;

    private String activityRemark;

    private Date applyTime;

    private Long applicantId;

    private String applicant;

    private Date auditTime;

    private Long auditorId;

    private String auditor;

    private Integer auditStatus;

    private String auditDesc;

	/**
	 *  活动规则
	 */
	private String activityRule;
}