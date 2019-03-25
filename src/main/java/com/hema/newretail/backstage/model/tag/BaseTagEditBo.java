package com.hema.newretail.backstage.model.tag;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public class BaseTagEditBo implements Serializable {

    private Long tagId;

    @NotBlank(message = "标签名不能为空")
    @Size(min=2, max=30,message = "标签名2到18个字符")
    private String tagName;

    @NotEmpty(message = "规则不可为空")
    @Valid
    private List<BaseTagRuleEditBo> tagRules;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<BaseTagRuleEditBo> getTagRules() {
        return tagRules;
    }

    public void setTagRules(List<BaseTagRuleEditBo> tagRules) {
        this.tagRules = tagRules;
    }
}