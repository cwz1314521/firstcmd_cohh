package com.hema.newretail.backstage.controller.financial;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.financial.AgentTransactionRecordCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.financial.AgentTransactionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller.financial
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-11 15:55
 */
@Api(description = "代理账户交易记录")
@RestController
@RequestMapping("/financial/agentTransactionRecord")
@AutoLog
public class AgentTransactionRecordController {

    @Autowired
    private AgentTransactionRecordService agentTransactionRecordService;

    @ApiOperation("查询代理账户交易记录")
    @PostMapping("/list")
    public Response list(@RequestBody AgentTransactionRecordCondition vo) {
        return agentTransactionRecordService.queryList(vo);
    }

    @ApiOperation("导出代理账户交易记录excel表格")
    @GetMapping("/excel")
    public Response excel(AgentTransactionRecordCondition vo, HttpServletResponse response) {
        return agentTransactionRecordService.excel(vo, response);
    }
}
