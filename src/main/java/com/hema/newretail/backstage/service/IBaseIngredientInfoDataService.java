package com.hema.newretail.backstage.service;

import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BaseIngredientInfoEntry;

import java.util.Map;

/**
 * 配料字典接口
 */
public interface IBaseIngredientInfoDataService {
    boolean save(BaseIngredientInfoEntry data);
    BaseIngredientInfoEntry findOneById(long id);
    Response findAll(Map<String,Object> params);
    String deleteById(long id);
    int verifyIngredientCode(String code);
}
