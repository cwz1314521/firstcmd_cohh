package com.hema.newretail.backstage.service.menu.impl;

import com.github.pagehelper.Page;
import com.hema.newretail.backstage.common.queryparam.menuclassify.MenuClassifyCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.BusiMenuClassifyMapper;
import com.hema.newretail.backstage.entry.BusiMenuClassify;
import com.hema.newretail.backstage.model.menu.MenuClassifyBo;
import com.hema.newretail.backstage.service.menu.MenuClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.menu.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-24 15:31
 */
@Service
public class MenuClassifyServiceImpl implements MenuClassifyService {

    @Autowired
    private BusiMenuClassifyMapper busiMenuClassifyMapper;

    /**
     * 根据查询条件查询饮品分类列表
     *
     * @param vo 查询条件对象
     * @return 返回对象
     */
    @Override
    public Response queryList(MenuClassifyCondition vo) {
        List<MenuClassifyBo> list = busiMenuClassifyMapper.selectList(vo, vo.getPageNum(), vo.getPageSize());
        return Response.success(list, ((Page) list).getTotal(), vo.getPageSize(), vo.getPageNum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insert(BusiMenuClassify vo) {
        long num = busiMenuClassifyMapper.selectCountByName(vo.getName());
        if (num > 0) {
            return Response.failure("分类名称已存在");
        }
        vo.setGmtCreate(new Date());
        busiMenuClassifyMapper.insert(vo);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response update(BusiMenuClassify vo) {
        vo.setGmtModify(new Date());
        busiMenuClassifyMapper.updateByPrimaryKeySelective(vo);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response delete(BusiMenuClassify vo) {
        Long num = busiMenuClassifyMapper.seelctMenuCountByMenuClassifyId(vo.getId());
        if (num > 0) {
            return Response.failure("该分类下有关联饮品，无法删除");
        }
        busiMenuClassifyMapper.deleteByPrimaryKey(vo.getId());
        return Response.success();
    }

}
