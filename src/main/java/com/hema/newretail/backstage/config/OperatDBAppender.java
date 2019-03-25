package com.hema.newretail.backstage.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.db.DBAppenderBase;
import com.alibaba.fastjson.JSON;
import com.hema.newretail.backstage.common.utils.IpUtil;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.SpringUtils;
import com.hema.newretail.backstage.entry.BaseUserInfoEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.config
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-22 13:47
 */
public class OperatDBAppender extends DBAppenderBase<ILoggingEvent> {

    private String insertSQL;
    private static final Method GET_GENERATED_KEYS_METHOD;

    private static final int OPERATOR_INDEX = 1;
    private static final int OP_TIME_INDEX = 2;
    private static final int OP_MENU_INDEX = 3;
    private static final int OP_CONTENT_INDEX = 4;
    private static final int OP_IP_INDEX = 5;

    static {
        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
        Method getGeneratedKeysMethod;
        try {
            // the
            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
        } catch (Exception ex) {
            getGeneratedKeysMethod = null;
        }
        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }

    @Override
    public void start() {
        insertSQL = MySQLBuilder.buildInsertSQL();
        super.start();
    }

    @Override
    protected Method getGeneratedKeysMethod() {
        return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
        return insertSQL;
    }

    @Override
    protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {

        bindLoggingMyInfoWithPreparedStatement(insertStatement, event);
        bindLoggingEventWithInsertStatement(insertStatement, event);

        System.out.println(insertStatement.toString());
        int updateCount = insertStatement.executeUpdate();
        if (updateCount != 1) {
            addWarn("Failed to insert loggingEvent");
        }
    }

    @Override
    protected void secondarySubAppend(ILoggingEvent event, Connection connection, long eventId) {

    }

    private void bindLoggingMyInfoWithPreparedStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IpUtil.getIpAddress(request);
        BaseUserInfoEntry userInfo = getUserInfo(request.getSession().getId());
        String operator = null == userInfo ? "" : userInfo.getRealName();
        stmt.setString(OPERATOR_INDEX, operator);
        stmt.setString(OP_CONTENT_INDEX, "");
        stmt.setString(OP_IP_INDEX, ip);
    }

    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        stmt.setLong(OP_TIME_INDEX, event.getTimeStamp());
        stmt.setString(OP_MENU_INDEX, event.getFormattedMessage());
    }

    @Override
    public void append(ILoggingEvent eventObject) {
        Connection connection = null;
        try {
            connection = connectionSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement insertStatement;
            insertStatement = connection.prepareStatement(getInsertSQL());
            // inserting an event and getting the result must be exclusive
            synchronized (this) {
                subAppend(eventObject, connection, insertStatement);
            }

            // we no longer need the insertStatement
            if (insertStatement != null) {
                insertStatement.close();
            }
            connection.commit();
        } catch (Throwable sqle) {
            addError("problem appending event", sqle);
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private BaseUserInfoEntry getUserInfo(String sessionId) {
        RedisUtils redisUtils = (RedisUtils) SpringUtils.getApplicationContext().getBean("redisUtils");
        String userInfoJson = redisUtils.hget(AuthConstants.SESSION + sessionId, AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if (StringUtils.isEmpty(userInfoJson)) {
            return null;
        }
        BaseUserInfoEntry userInfo = JSON.parseObject(userInfoJson, BaseUserInfoEntry.class);
        if (null == userInfo || null == userInfo.getPostId()) {
            return null;
        }
        return userInfo;
    }

}
