package com.hema.newretail.backstage.model.taskkafka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.taskkafka
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-21 11:30
 */
@Getter
@Setter
@NoArgsConstructor
public class ModifyZoneBoxGroupBo {
    private Long zoneId;
    private Long oldGroupId;
    private Long newGroupId;
    private Long timestamp;
}
