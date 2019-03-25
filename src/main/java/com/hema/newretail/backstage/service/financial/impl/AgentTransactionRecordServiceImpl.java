package com.hema.newretail.backstage.service.financial.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.backstage.common.queryparam.financial.AgentTransactionRecordCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.excel.PoiExcelHelper;
import com.hema.newretail.backstage.dao.BusiAccountLogMapper;
import com.hema.newretail.backstage.model.financial.AgentTransactionRecordBo;
import com.hema.newretail.backstage.service.financial.AgentTransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.financial.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-11 15:57
 */
@Service
public class AgentTransactionRecordServiceImpl implements AgentTransactionRecordService {

    @Autowired
    private BusiAccountLogMapper busiAccountLogMapper;

    @Override
    public Response queryList(AgentTransactionRecordCondition vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<AgentTransactionRecordBo> list = busiAccountLogMapper.selectList(vo);
        return Response.success(list, ((Page) list).getTotal(), vo.getPageSize(), vo.getPageNum());
    }

    @Override
    public Response excel(AgentTransactionRecordCondition vo, HttpServletResponse response) {
        vo.setPageNum(null);
        vo.setPageSize(null);
        List<AgentTransactionRecordBo> list = busiAccountLogMapper.selectList(vo);
        String[] titles = {"代理公司", "交易类型", "收益账户", "押金账户", "冻结金额", "原料账户", "发生时间", "备注"};
        PoiExcelHelper excelHelper = new PoiExcelHelper("代理账户交易记录表", titles);
        if (null != list) {
            // 创建表头
            excelHelper.createTitle();
            int rowNum = 1;
            for (AgentTransactionRecordBo data : list) {
                // 生成excel内容
                String[] contents = new String[8];
                contents[0] = null == data.getAgentCompanyName() ? "" : data.getAgentCompanyName();
                contents[1] = null == data.getActionType() ? "" : data.getActionType();
                contents[2] = null == data.getIncomeAccount() ? "" : data.getIncomeAccount().toString();
                contents[3] = null == data.getDepositAccount() ? "" : data.getDepositAccount().toString();
                contents[4] = null == data.getFreezAmount() ? "" : data.getFreezAmount().toString();
                contents[5] = null == data.getMaterialAccount() ? "" : data.getMaterialAccount().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                contents[6] = null == data.getCreateDate() ? "" : sdf.format(data.getCreateDate());
                contents[7] = null == data.getRemark() ? "" : data.getRemark();
                excelHelper.createData(rowNum++, contents);
            }
        }
        //浏览器下载excel
        excelHelper.buildExcelDocument(response);
        return Response.success();
    }


}
