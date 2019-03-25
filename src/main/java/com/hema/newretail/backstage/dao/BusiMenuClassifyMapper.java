package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.menuclassify.MenuClassifyCondition;
import com.hema.newretail.backstage.entry.BusiMenuClassify;
import com.hema.newretail.backstage.model.menu.AllMenuClassifyBo;
import com.hema.newretail.backstage.model.menu.MenuClassifyBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * zhs
 */
public interface BusiMenuClassifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusiMenuClassify record);

    int insertSelective(BusiMenuClassify record);

    BusiMenuClassify selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiMenuClassify record);

    int updateByPrimaryKey(BusiMenuClassify record);


    /**
     * 根据查询条件，查询饮品分类列表
     *
     * @param vo       查询条件对象
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return list
     */
    List<MenuClassifyBo> selectList(@Param("vo") MenuClassifyCondition vo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * 根据分类名称查询符合条件的记录数量
     *
     * @param name 分类名称
     * @return 记录数量
     */
    Long selectCountByName(String name);

    /**
     * 根据分类ID查询该分类下关联的饮品数量
     *
     * @param id 分类ID
     * @return 饮品数量
     */
    Long seelctMenuCountByMenuClassifyId(Long id);

    /**
     * 查询所有的饮品分类
     *
     * @return 集合
     */
    List<AllMenuClassifyBo> selectAll();
}