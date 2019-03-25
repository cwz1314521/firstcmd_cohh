package com.hema.newretail.backstage.entry;

import com.hema.newretail.backstage.common.validator.First;
import com.hema.newretail.backstage.common.validator.Second;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 */
@Data
public class BusiIngredientMenuImageText implements Serializable {
    private static final long serialVersionUID = -4751025996803129251L;
    private Long id;

    @NotBlank(message = "标题不能为空", groups = {First.class})
    private String title;

    private String picUrl;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String content;

    private Long menuId;

}