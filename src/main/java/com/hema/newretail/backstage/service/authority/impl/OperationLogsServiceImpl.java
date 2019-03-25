package com.hema.newretail.backstage.service.authority.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.backstage.common.queryparam.authority.OperationLogsCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.BaseOperationLogsMapper;
import com.hema.newretail.backstage.entry.BaseOperationLogs;
import com.hema.newretail.backstage.service.authority.OperationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.authority.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-23 16:02
 */
@Service
public class OperationLogsServiceImpl implements OperationLogsService {
    @Autowired
    private BaseOperationLogsMapper baseOperationLogsMapper;

    @Override
    public Response queryList(OperationLogsCondition vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<BaseOperationLogs> list = baseOperationLogsMapper.selectList(vo);
        return Response.success(list, ((Page) list).getTotal(), vo.getPageSize(), vo.getPageNum());
    }
}
