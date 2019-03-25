package com.hema.newretail.backstage.common.queryparam.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.common
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-02-26 9:53
 */
@NoArgsConstructor
@Getter
@Setter
public class MapGeohashTagCondition {
    // 当前可视范围内的geo hash
    private List<String> geohashs;
}
