package com.hema.newretail.backstage.model.taskkafka;

import com.hema.newretail.backstage.entry.BaseIngredientBoxEntry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.taskkafka
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-21 13:49
 */
@Setter
@Getter
@NoArgsConstructor
public class IngredientBoxBo implements Serializable {
    private static final long serialVersionUID = -2519403568929243505L;
    private Integer boxCode;
    private Long ingredientId;

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        BaseIngredientBoxEntry bo = (BaseIngredientBoxEntry) o;
        return Objects.equals(boxCode, bo.getBoxCode()) &&
                Objects.equals(ingredientId, bo.getIngredientId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxCode, ingredientId);
    }
}
