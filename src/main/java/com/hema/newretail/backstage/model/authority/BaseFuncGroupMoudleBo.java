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
public class BaseFuncGroupMoudleBo implements Serializable {

    private Long postId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    private String moudleCode;

    private String moudleName;

    private List<BaseFuncGroupIsSelectEntry> list;

    public String getMoudleCode() {
        return moudleCode;
    }

    public void setMoudleCode(String moudleCode) {
        this.moudleCode = moudleCode;
    }

    public String getMoudleName() {
        return moudleName;
    }

    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    public List<BaseFuncGroupIsSelectEntry> getList() {
        return list;
    }

    public void setList(List<BaseFuncGroupIsSelectEntry> list) {
        this.list = list;
    }
}