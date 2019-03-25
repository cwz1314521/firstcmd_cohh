package com.hema.newretail.backstage.common.queryparam.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @Department 新零售
 * @ClassName UpdateByMachinePicCondition
 * @Description 循环更新片区表参数类
 * @Author ---CWZ
 * @Date 2018/12/24 15:23
 * @Version 1.0
 **/

@Data
public class UpdateByMachinePicCondition {
    @JsonIgnore
    private List<Long> id;
    /**
     * 当属性值为0默认全部 当属性为1则为选定的部分
     */
    private String type;

    private Long mId;

}
