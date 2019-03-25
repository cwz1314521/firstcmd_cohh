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
public class MenuMarker implements Serializable {
    private static final long serialVersionUID = -161277792475781686L;

    @NotNull(message = "id不允许为空", groups = {Second.class})
    private Long id;

    @NotBlank(message = "角标名称不允许为空", groups = {First.class})
    private String markerName;

    private Integer position;

    private String androidPicUrl;

    private String iosPicUrl;

    private Integer status;

    private Integer isDeleted;

    private Date gmtCreate;

    private Date gmtModify;
}