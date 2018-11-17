package com.hema.newretail.backstage.service.mobile.impl;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.mobile.IngredientNameByIdCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.BaseIngredientInfoEntryMapper;
import com.hema.newretail.backstage.entry.BaseIngredientInfoEntry;
import com.hema.newretail.backstage.service.mobile.FileOSSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Department 新零售
 * @ClassName FileOSSServiceImpl
 * @Description 提供给文件中转操作
 * @Author ---CWZ
 * @Date 2018/11/16 17:18
 * @Version 1.0
 **/
@Service
public class FileOSSServiceImpl implements FileOSSService {


    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    @Autowired
    private BaseIngredientInfoEntryMapper baseIngredientInfoEntryMapper;
    /**
     * 功能描述: 根据原料id查询原料名字 ---  加入redis缓存
     *
     * @param ingredientNameByIdCondition
     * @param: ingredientNameByIdCondition
     * @return: name
     * @author: cwz
     * @date: 2018/11/16 10:22
     */
    @Override
    public Response IngredientNameById(IngredientNameByIdCondition ingredientNameByIdCondition) {

        logger.info("根据id查询料名"+ingredientNameByIdCondition.getId());
        BaseIngredientInfoEntry entry = baseIngredientInfoEntryMapper.selectByPrimaryKey(ingredientNameByIdCondition.getId());
        if(entry != null){
            return Response.success(entry.getIngredientName());
        }else {
            logger.error("没有检索到数据");
            return Response.failure("没有数据");
        }

    }
}
