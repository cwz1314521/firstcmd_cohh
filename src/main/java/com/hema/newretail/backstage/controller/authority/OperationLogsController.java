package com.hema.newretail.backstage.controller.authority;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.authority.OperationLogsCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.authority.OperationLogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller.authority
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-23 16:01
 */
@Api(description = "操作日志")
@AutoLog
@RestController
@RequestMapping("/logs")
public class OperationLogsController {
    @Autowired
    private OperationLogsService operationLogsService;

    @ApiOperation("查看日志记录")
    @PostMapping("/list")
    public Response list(@RequestBody OperationLogsCondition vo) {
        return operationLogsService.queryList(vo);
    }
}
