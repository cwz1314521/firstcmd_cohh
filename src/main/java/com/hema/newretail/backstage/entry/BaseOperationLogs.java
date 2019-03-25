package com.hema.newretail.backstage.entry;

import java.io.Serializable;

/**
 * @author admin
 */
public class BaseOperationLogs implements Serializable {
    private static final long serialVersionUID = 5638864457386286421L;
    private Long id;

    private String operator;

    private Long opTime;

    private String opMenu;

    private String opContent;

    private String opIp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Long getOpTime() {
        return opTime;
    }

    public void setOpTime(Long opTime) {
        this.opTime = opTime;
    }

    public String getOpMenu() {
        return opMenu;
    }

    public void setOpMenu(String opMenu) {
        this.opMenu = opMenu == null ? null : opMenu.trim();
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent == null ? null : opContent.trim();
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp == null ? null : opIp.trim();
    }
}