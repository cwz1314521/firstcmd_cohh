package com.hema.newretail.backstage.config;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.config
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-22 14:32
 */
public class MySQLBuilder {
    static String buildInsertSQL() {
        return "INSERT INTO `base_operation_logs` (`operator`, `op_time`, `op_menu`, `op_content`, `op_ip`) " +
                "VALUES (?, ?, ? ,?, ?)";
    }
}
