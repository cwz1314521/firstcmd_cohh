package com.hema.newretail.backstage.service.mobile;

import com.hema.newretail.backstage.common.utils.Response;

/**
 * @Department 新零售
 * @ClassName FileOSSService
 * @Description 提供给文件中转操作
 * @Author ---CWZ
 * @Date 2018/11/16 17:18
 * @Version 1.0
 **/
public interface FileOSSService {
    /**
     *
     * 功能描述: 根据原料id查询原料名字 ---  加入redis缓存
     *
     * @param: ingredientNameByIdCondition
     * @return: name
     * @author: cwz
     * @date: 2018/11/16 10:22
     */
    Response IngredientNameById(Long  id);

    /**
     *
     * 功能描述: 机器信息
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/27 10:06
     */
    Response machine(String uuid);


}
