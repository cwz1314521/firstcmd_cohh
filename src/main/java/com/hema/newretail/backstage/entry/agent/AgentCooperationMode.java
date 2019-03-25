package com.hema.newretail.backstage.entry.agent;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgentCooperationMode implements Serializable {
    private Long id;

    private Long agentUserId;

    private BigDecimal cooperationMode;

    private Date gmtCreate;

    private Date effectiveTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(Long agentUserId) {
        this.agentUserId = agentUserId;
    }

    public BigDecimal getCooperationMode() {
        return cooperationMode;
    }

    public void setCooperationMode(BigDecimal cooperationMode) {
        this.cooperationMode = cooperationMode;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}