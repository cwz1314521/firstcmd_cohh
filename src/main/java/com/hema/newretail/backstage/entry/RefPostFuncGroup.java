package com.hema.newretail.backstage.entry;

import java.io.Serializable;

public class RefPostFuncGroup implements Serializable {
    private Long postId;

    private String funcGroupCode;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getFuncGroupCode() {
        return funcGroupCode;
    }

    public void setFuncGroupCode(String funcGroupCode) {
        this.funcGroupCode = funcGroupCode == null ? null : funcGroupCode.trim();
    }
}