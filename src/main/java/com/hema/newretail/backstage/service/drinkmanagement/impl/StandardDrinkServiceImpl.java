package com.hema.newretail.backstage.service.drinkmanagement.impl;

import com.hema.newretail.backstage.common.queryparam.menu.BusiIngredientMenuImageTextCondition;
import com.hema.newretail.backstage.common.queryparam.menu.MenuImageTextCondition;
import com.hema.newretail.backstage.common.queryparam.menu.UpdateRecommendCondition;
import com.hema.newretail.backstage.common.requestparam.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.*;
import com.hema.newretail.backstage.entry.*;
import com.hema.newretail.backstage.service.drinkmanagement.StandardDrinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by jiahao on 2018-08-25
 */
@Service
public class StandardDrinkServiceImpl implements StandardDrinkService {

    @Autowired
    BusiIngredientMenuMapper busiIngredientMenuEntryMapper;

    @Autowired
    RefMenuIngredientEntryMapper refMenuIngredientEntryMapper;

    @Autowired
    BaseMenuPropertiesEntryMapper baseMenuPropertiesEntryMapper;

    @Autowired
    BaseTagRuleEntryMapper baseTagRuleEntryMapper;

    @Autowired
    BusiIngredientMenuImageTextMapper busiIngredientMenuImageTextMapper;

    /**
     * 查询有无添加过饮品
     *
     * @param menuName
     * @return
     */
    @Override
    public BusiIngredientMenuEntry selectStandardDrink(String menuName) {
        BusiIngredientMenuEntry busiIngredientMenuEntry = busiIngredientMenuEntryMapper.selectStandardDrink(menuName);
        return busiIngredientMenuEntry;
    }

    @Override
    public String selectStandardDrink(String menuName, Long id) {
        BusiIngredientMenuEntry busiIngredientMenuEntry = busiIngredientMenuEntryMapper.selectStandardDrink(menuName);
        if (busiIngredientMenuEntry == null) {
            return "SUCCESS";
        }
        Long menuEntryId = busiIngredientMenuEntry.getId();
        if (id.equals(menuEntryId) && menuName.equals(busiIngredientMenuEntry.getMenuName())) {
            return "OK";
        } else {
            return "ERROR";
        }
    }

    /**
     * 添加与修改标准饮品
     *
     * @param menuParam 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndUpdateStandardDrink(MenuParam menuParam) {
        BusiIngredientMenuEntry busiIngredientMenuEntry = new BusiIngredientMenuEntry();
        busiIngredientMenuEntry.setMenuName(menuParam.getMenuName());
        busiIngredientMenuEntry.setPrice(menuParam.getPrice());
        busiIngredientMenuEntry.setSmallPic(menuParam.getSmallPic());
        busiIngredientMenuEntry.setMiddlePic(menuParam.getMiddlePic());
        busiIngredientMenuEntry.setBigPic(menuParam.getBigPic());
        busiIngredientMenuEntry.setAnyPic(menuParam.getAnyPic());
        busiIngredientMenuEntry.setStatus(menuParam.getStatus());
        busiIngredientMenuEntry.setIsDiy(0L);
        busiIngredientMenuEntry.setRecommendOrder(menuParam.getRecommendOrder());
        busiIngredientMenuEntry.setIsRecommend(menuParam.getIsRecommend());
        busiIngredientMenuEntry.setIsDeleted(0L);

        busiIngredientMenuEntry.setMenuClassifyId(menuParam.getMenuClassifyId());
        busiIngredientMenuEntry.setSubTitle(menuParam.getSubTitle());
        busiIngredientMenuEntry.setMenuDesc(menuParam.getMenuDesc());
        // 2019-02-28 add
        busiIngredientMenuEntry.setShowOrder(menuParam.getShowOrder());

        if (StringUtils.isEmpty(menuParam.getId())) {
            busiIngredientMenuEntry.setGmtCreate(new Date());
            busiIngredientMenuEntry.setGmtModified(new Date());
            busiIngredientMenuEntryMapper.saveStandardDrink(busiIngredientMenuEntry);
        } else {
            busiIngredientMenuEntry.setId(menuParam.getId());
            busiIngredientMenuEntry.setGmtModified(new Date());
            busiIngredientMenuEntryMapper.updateByPrimaryKeySelective(busiIngredientMenuEntry);
        }


        Long id = busiIngredientMenuEntry.getId();
        List<MaterialParam> materialParamList = menuParam.getMaterialParamList();
        RefMenuIngredientEntry refMenuIngredientEntry;
        if (StringUtils.isEmpty(menuParam.getId())) {
            for (MaterialParam materialParam : materialParamList) {
                refMenuIngredientEntry = new RefMenuIngredientEntry();
                refMenuIngredientEntry.setIngredientMenuId(id);
                refMenuIngredientEntry.setIngredientId(materialParam.getIngredientId());
                refMenuIngredientEntry.setNum(materialParam.getNum());
                refMenuIngredientEntryMapper.saveMaterialList(refMenuIngredientEntry);
            }
        } else {
            refMenuIngredientEntryMapper.deleteByIngredientMenuId(menuParam.getId());
            for (MaterialParam materialParam : materialParamList) {
                refMenuIngredientEntry = new RefMenuIngredientEntry();
                refMenuIngredientEntry.setIngredientId(materialParam.getIngredientId());
                refMenuIngredientEntry.setNum(materialParam.getNum());
                refMenuIngredientEntry.setIngredientMenuId(menuParam.getId());
                refMenuIngredientEntryMapper.saveMaterialList(refMenuIngredientEntry);
            }
        }


        List<OptionParam> optionParamList = menuParam.getOptionParamList();
        if (StringUtils.isEmpty(menuParam.getId())) {
            for (OptionParam optionParam : optionParamList) {
                BaseMenuPropertiesEntry baseMenuPropertiesEntry = null;
                Long proType = optionParam.getProType();
                Boolean frontShow = optionParam.getIsFrontShow();
                List<ChoiceParam> optionChoiceList = optionParam.getOptionChoice();
                for (ChoiceParam optionChoice : optionChoiceList) {
                    baseMenuPropertiesEntry = new BaseMenuPropertiesEntry();
                    baseMenuPropertiesEntry.setMenuId(id);
                    baseMenuPropertiesEntry.setIsDeleted(new Byte("0"));
                    baseMenuPropertiesEntry.setProName(optionChoice.getProName());
                    baseMenuPropertiesEntry.setProType(proType);
                    baseMenuPropertiesEntry.setPrice(optionChoice.getPrice());
                    baseMenuPropertiesEntry.setNum(optionChoice.getNum());
                    baseMenuPropertiesEntry.setStatus(optionChoice.getStatus());
                    baseMenuPropertiesEntry.setGmtCreate(new Date());
                    baseMenuPropertiesEntry.setIsFrontShow(frontShow);
                    baseMenuPropertiesEntryMapper.saveMenuProperties(baseMenuPropertiesEntry);
                }
            }
        } else {
            for (OptionParam optionParam : optionParamList) {
                BaseMenuPropertiesEntry baseMenuPropertiesEntry = null;
                Long proType = optionParam.getProType();
                Boolean frontShow = optionParam.getIsFrontShow();
                List<ChoiceParam> optionChoiceList = optionParam.getOptionChoice();
                for (ChoiceParam optionChoice : optionChoiceList) {
                    baseMenuPropertiesEntry = new BaseMenuPropertiesEntry();
                    baseMenuPropertiesEntry.setMenuId(menuParam.getId());
                    baseMenuPropertiesEntry.setGmtModified(new Date());
                    baseMenuPropertiesEntry.setIsDeleted(new Byte("0"));
                    baseMenuPropertiesEntry.setProName(optionChoice.getProName());
                    baseMenuPropertiesEntry.setProType(proType);
                    baseMenuPropertiesEntry.setPrice(optionChoice.getPrice());
                    baseMenuPropertiesEntry.setNum(optionChoice.getNum());
                    baseMenuPropertiesEntry.setStatus(optionChoice.getStatus());
                    baseMenuPropertiesEntry.setGmtCreate(new Date());
                    baseMenuPropertiesEntry.setIsFrontShow(frontShow);
                    baseMenuPropertiesEntry.setId(optionChoice.getId());
                    baseMenuPropertiesEntryMapper.updateByPrimaryKeySelective(baseMenuPropertiesEntry);
                }
            }
        }

        //编辑时标签的修改
        if (!StringUtils.isEmpty(menuParam.getId())) {
            BaseTagRuleEntry baseTagRuleEntry = null;
            List<TagParam> tagParamList = menuParam.getTagParamList();
            Long menuId = menuParam.getId();
            baseTagRuleEntryMapper.deleteByMenuId(menuId);
            for (TagParam tagParam : tagParamList) {
                baseTagRuleEntry = new BaseTagRuleEntry();
                baseTagRuleEntry.setTagId(tagParam.getTagId());
                baseTagRuleEntry.setRuleType(true);
                baseTagRuleEntry.setMenuId(menuId);
                baseTagRuleEntry.setMenuName(menuParam.getMenuName());
                baseTagRuleEntry.setNum(tagParam.getNum());
                baseTagRuleEntryMapper.insert(baseTagRuleEntry);
            }
        }
    }

    /**
     * 设置首页推荐
     *
     * @param menuParam
     * @return
     */
    @Override
    public Response updateRecommend(UpdateRecommendCondition menuParam) {
        BusiIngredientMenuEntry busiIngredientMenuEntry = new BusiIngredientMenuEntry();
        busiIngredientMenuEntry.setId(menuParam.getId());
        busiIngredientMenuEntry.setIsRecommend(menuParam.getIsRecommend());
        busiIngredientMenuEntry.setRecommendOrder(menuParam.getRecommendOrder());
        int num = busiIngredientMenuEntryMapper.updateByPrimaryKeySelective(busiIngredientMenuEntry);
        if (num > 0) {
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    @Override
    public Response imageTextDetail(MenuImageTextCondition condition) {
        return Response.success(busiIngredientMenuImageTextMapper.selectByPrimaryKey(condition.getId()));
    }

    @Override
    public Response updateMenuImageText(BusiIngredientMenuImageTextCondition condition) {
        BusiIngredientMenuImageText data = new BusiIngredientMenuImageText();
        BeanUtils.copyProperties(condition, data);
        if (null == data.getId()) {
            data.setGmtCreate(new Date());
            data.setGmtModified(new Date());
            data.setIsDeleted(0);
            busiIngredientMenuImageTextMapper.insert(data);
        } else {
            data.setIsDeleted(0);
            data.setGmtModified(new Date());
            busiIngredientMenuImageTextMapper.updateByPrimaryKeySelective(data);
        }
        return Response.success();
    }

}
