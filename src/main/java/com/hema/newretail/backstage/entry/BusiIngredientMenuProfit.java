package com.hema.newretail.backstage.entry;

import com.hema.newretail.backstage.common.validator.First;
import com.hema.newretail.backstage.common.validator.Second;
import com.hema.newretail.backstage.common.validator.Third;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author admin
 */
@Data
public class BusiIngredientMenuProfit implements Serializable {
    private static final long serialVersionUID = -5738195026142117258L;

    @NotNull(message = "ID不能为空", groups = {Second.class})
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    @NotNull(message = "饮品ID不能为空", groups = {First.class})
    private Long ingredientMenuId;

    @DecimalMin(value = "0", message = "销售价格最低为{value}", groups = {First.class})
    private BigDecimal salePrice;
    @DecimalMin(value = "0", message = "每杯分润最低为{value}", groups = {First.class})
    private BigDecimal profitMoney;

    @NotNull(message = "开始时间不能为空", groups = {First.class})
    private Date startTime;

    @NotNull(message = "结束时间不能为空", groups = {First.class})
    private Date endTime;

    private String operator;

    @NotNull(message = "是否启用不能为空", groups = {Third.class})
    private Boolean isDeleted;

    @Length(max = 50, message = "备注最多输入50个字")
    private String remark;

}