package com.hema.newretail.backstage.entry;

import com.hema.newretail.backstage.model.common.BasePage;

import java.util.Date;

public class VFinanceAuditing extends BasePage{
    private Long id;

    private String title;

    private Long type;

    private String auditcontent;

    private Date applytime;

    private String applicant;

    private Integer auditstatus;

    private String auditor;

    private Date audittime;

    private String auditdesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getAuditcontent() {
        return auditcontent;
    }

    public void setAuditcontent(String auditcontent) {
        this.auditcontent = auditcontent == null ? null : auditcontent.trim();
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public Integer getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(Integer auditstatus) {
        this.auditstatus = auditstatus;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getAuditdesc() {
        return auditdesc;
    }

    public void setAuditdesc(String auditdesc) {
        this.auditdesc = auditdesc == null ? null : auditdesc.trim();
    }
}