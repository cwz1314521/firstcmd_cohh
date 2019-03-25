package com.hema.newretail.backstage.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.common
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-02-26 10:51
 */
@NoArgsConstructor
@Getter
@Setter
public class MapGeohashTagBo {
    /**
     * geo hash code
     */
    private String hashcode;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 维度
     */
    private Double latitude;
}
