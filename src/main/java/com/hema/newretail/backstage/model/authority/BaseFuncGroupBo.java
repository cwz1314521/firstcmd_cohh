package com.hema.newretail.backstage.model.authority;


import com.hema.newretail.backstage.entry.BaseFuncGroupIsSelectEntry;

import java.io.Serializable;
import java.util.List;

public class BaseFuncGroupBo implements Serializable {

    private String funcGroupCode;

    private String funcGroupName;

    private String isSelected;

    public String getFuncGroupCode() {
        return funcGroupCode;
    }

    public void setFuncGroupCode(String funcGroupCode) {
        this.funcGroupCode = funcGroupCode;
    }

    public String getFuncGroupName() {
        return funcGroupName;
    }

    public void setFuncGroupName(String funcGroupName) {
        this.funcGroupName = funcGroupName;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}