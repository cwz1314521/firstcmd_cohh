package com.hema.newretail.backstage.entry;

import com.hema.newretail.backstage.common.validator.Second;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author admin
 */
@Data
public class RefMenuMarker implements Serializable {
    private static final long serialVersionUID = -2069023909225855386L;
    @NotNull(message = "id不允许为空", groups = {Second.class})
    private Long id;

    private Long menuId;

    private Long markerId;
}