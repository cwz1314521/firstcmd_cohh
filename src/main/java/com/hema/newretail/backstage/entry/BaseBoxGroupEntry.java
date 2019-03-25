package com.hema.newretail.backstage.entry;
/**
 *
 * 功能描述: BaseBoxGroupEntry

 * @author  cwz
 * @date  2018/12/10 10:41
 */
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@Data
public class BaseBoxGroupEntry implements Serializable {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**名字*/
    private String name;

    /**描述*/
    private String desc;

    /**改时间*/
    private Date gmtModified;

    /**创建时间*/
    private Date gmtCreate;

    /**0 正常 1 删除*/
    private Boolean isDeleted;

    /**机器类型*/
    private Long machineTypeId;


}