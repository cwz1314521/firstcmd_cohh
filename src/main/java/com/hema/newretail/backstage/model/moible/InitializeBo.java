package com.hema.newretail.backstage.model.moible;

import lombok.Data;

import java.util.List;

/**
 * @Department 新零售
 * @ClassName InitializeBo
 * @Description 安卓端初始化
 * @Author ---CWZ
 * @Date 2018/11/27 10:18
 * @Version 1.0
 **/
@Data
public class InitializeBo {

    private String tel;

    private String slogan;

    private List<String> nickname;

    private List<VioceBo> voice;

    private Integer cups;
}
