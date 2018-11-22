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
 * @date 2018-11-21 13:47
 */
@Setter
@Getter
@NoArgsConstructor
public class ModifyBoxGroupBo {
    private Long groupId;
    private List<IngredientBoxBo> ingredientBox;
    private Long timestamp;
}
