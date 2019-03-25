package com.hema.newretail.backstage.model.tag;

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
public class MenuMarkerBo implements Serializable {
    private static final long serialVersionUID = 4893829551150122597L;
    private Long id;

    private String markerName;

    private Integer position;

    private String androidPicUrl;

    private String iosPicUrl;

    private Integer status;

    private Integer isDeleted;

    private Date gmtCreate;

    private Integer menuNum;
}