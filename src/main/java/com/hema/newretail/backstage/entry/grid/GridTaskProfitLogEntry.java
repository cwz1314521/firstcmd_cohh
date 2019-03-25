package com.hema.newretail.backstage.entry.grid;
/**
 * @author cwz
 */

import java.util.Date;

public class GridTaskProfitLogEntry {
    private Long id;

    private String event;

    private Date operationTime;

    private String status;

    private Double profit;

    private Long gridCompanyId;

    private Date gmtCreateTime;

    private Date gmtModifiedTime;

    private String questionNote;

    private Date questionTime;

    private String replyNote;

    private Date replyTime;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Long getGridCompanyId() {
        return gridCompanyId;
    }

    public void setGridCompanyId(Long gridCompanyId) {
        this.gridCompanyId = gridCompanyId;
    }

    public Date getGmtCreateTime() {
        return gmtCreateTime;
    }

    public void setGmtCreateTime(Date gmtCreateTime) {
        this.gmtCreateTime = gmtCreateTime;
    }

    public Date getGmtModifiedTime() {
        return gmtModifiedTime;
    }

    public void setGmtModifiedTime(Date gmtModifiedTime) {
        this.gmtModifiedTime = gmtModifiedTime;
    }

    public String getQuestionNote() {
        return questionNote;
    }

    public void setQuestionNote(String questionNote) {
        this.questionNote = questionNote == null ? null : questionNote.trim();
    }

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public String getReplyNote() {
        return replyNote;
    }

    public void setReplyNote(String replyNote) {
        this.replyNote = replyNote == null ? null : replyNote.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}