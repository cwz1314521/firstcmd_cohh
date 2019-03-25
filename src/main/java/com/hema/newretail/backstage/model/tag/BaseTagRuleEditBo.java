package com.hema.newretail.backstage.model.tag;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public class BaseTagRuleEditBo implements Serializable {
    @NotNull(message = "规则类型不可为空")
    private Boolean ruleType;
    @NotNull(message = "饮品ID不可为空")
    private Long menuId;

    @NotBlank(message = "饮品名不可为空")
    private String menuName;

    @NotNull(message = "数量不可为空")
    @Max(value = 99,message = "数量输入范围有误 1-99")
    @Min(value = 1,message = "数量输入范围有误 1-99")
    private Integer num;

    public Boolean getRuleType() {
        return ruleType;
    }

    public void setRuleType(Boolean ruleType) {
        this.ruleType = ruleType;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}