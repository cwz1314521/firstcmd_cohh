package com.hema.newretail.backstage.model.index;

import com.hema.newretail.backstage.entry.IndexpageDetailEntry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * hema-newetaril-com.hema.newretail.backstage.model
 *
 * @author ZhangHaiSheng
 * @date 2018-08-20 17:26
 */
@Getter
@Setter
@NoArgsConstructor
public class IndexConfigBo implements Serializable {
    private static final long serialVersionUID = 7485358242553281184L;
    private Long cssId;
    private String cssAbstract;
    private Short cssType;
    private Integer order;
    private Long configId;

    private List<IndexpageDetailBo> details;
}
