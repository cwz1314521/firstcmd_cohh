package com.hema.newretail.backstage.model.taskkafka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.taskkafka
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-20 15:40
 */
@Getter
@Setter
@NoArgsConstructor
public class AddZoneHashBo {
    private Long zoneId;
    private String[] geoHash;
    private Long timestamp;
}
