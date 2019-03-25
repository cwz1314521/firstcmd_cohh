package com.hema.newretail.backstage.model.data;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Department 新零售
 * @ClassName ListBo
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/12/19 15:22
 * @Version 1.0
 **/
@Data
public class ListBo {

    private List<String> date;

    private TreeSet<String> set;

    private List<Map> map;
}
