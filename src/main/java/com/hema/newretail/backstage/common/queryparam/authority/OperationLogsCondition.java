package com.hema.newretail.backstage.common.queryparam.authority;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.authority
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-23 16:07
 */
@Data
public class OperationLogsCondition {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String startDate;
    private String endDate;
}
