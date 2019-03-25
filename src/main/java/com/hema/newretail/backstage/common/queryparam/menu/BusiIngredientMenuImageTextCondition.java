package com.hema.newretail.backstage.common.queryparam.menu;

import com.hema.newretail.backstage.common.validator.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 */
@Data
public class BusiIngredientMenuImageTextCondition implements Serializable {
    private static final long serialVersionUID = -4751025996803129251L;
    private Long id;

    @NotNull(message = "饮品ID不允许为空", groups = {First.class})
    private Long menuId;

    @NotBlank(message = "标题不能为空", groups = {First.class})
    private String title;

    private String picUrl;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String content;

}