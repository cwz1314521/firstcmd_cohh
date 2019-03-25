package com.hema.newretail.backstage.entry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 **/
@NoArgsConstructor
@Getter
@Setter
public class BasePost implements Serializable {
    private static final long serialVersionUID = -277316640754844193L;
    private Long id;

    @NotBlank(message = "参数postName不能为空")
    private String postName;

    private String postDesc;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;

    private Integer status;

}