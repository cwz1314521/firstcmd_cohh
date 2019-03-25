package com.hema.newretail.backstage.model.authority;


import com.hema.newretail.backstage.entry.BaseFuncGroupIsSelectEntry;

import java.io.Serializable;
import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
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