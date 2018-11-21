package com.hema.newretail.backstage.entry.grid;

import java.io.Serializable;

public class GridReplenishmentTaskEntry implements Serializable {
    private Long boxId;

    private Long taskId;

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}