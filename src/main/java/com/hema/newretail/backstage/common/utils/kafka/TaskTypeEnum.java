package com.hema.newretail.backstage.common.utils.kafka;

/**
 * machine-statics-com.hema.realtimestatics.realtimestatics.topology.bolt.enums
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-13 15:27
 */
public enum TaskTypeEnum {
    //1补货、2换件，3保洁、4维修、5巡检、6换料
    NULL(0, ""),
    SUPPLY_GOODS(1, "补货"),
    REPLACE(2, "换件"),
    CLEAN_UP(3, "保洁"),
    REPAIR(4, "维修"),
    INSPECTION(5, "巡检"),
    CHANGE_MATERIAL(6, "换料");

    private int code;
    private String text;

    TaskTypeEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据code获取枚举
     *
     * @param code code
     * @return 枚举
     */
    public static TaskTypeEnum getNameByCode(int code) {
        for (TaskTypeEnum type : TaskTypeEnum.values()) {
            if (type.ordinal() == code) {
                return type;
            }
        }
        return TaskTypeEnum.NULL;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
