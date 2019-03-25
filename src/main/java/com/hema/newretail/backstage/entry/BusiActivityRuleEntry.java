package com.hema.newretail.backstage.entry;

import java.util.Date;

public class BusiActivityRuleEntry {
    private Long id;

    private String ruleName;

    private Integer perDayCount;

    private Integer perDaySingalCount;

    private Integer singalTotalCount;

    private Integer activeObject;

    private String createname;

    private Long createId;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String ruleCondition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Integer getPerDayCount() {
        return perDayCount;
    }

    public void setPerDayCount(Integer perDayCount) {
        this.perDayCount = perDayCount;
    }

    public Integer getPerDaySingalCount() {
        return perDaySingalCount;
    }

    public void setPerDaySingalCount(Integer perDaySingalCount) {
        this.perDaySingalCount = perDaySingalCount;
    }

    public Integer getSingalTotalCount() {
        return singalTotalCount;
    }

    public void setSingalTotalCount(Integer singalTotalCount) {
        this.singalTotalCount = singalTotalCount;
    }

    public Integer getActiveObject() {
        return activeObject;
    }

    public void setActiveObject(Integer activeObject) {
        this.activeObject = activeObject;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRuleCondition() {
        return ruleCondition;
    }

    public void setRuleCondition(String ruleCondition) {
        this.ruleCondition = ruleCondition == null ? null : ruleCondition.trim();
    }
}