package com.hema.newretail.backstage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.config
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-02-14 13:50
 */
@Configuration
public class RedisTemplateConfiguration {

    @Bean(name = "staticsRedisTemplate")
    public RedisTemplate<String, String> staticsRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        RedisSerializer<String> valueRedisSerializer = new StringRedisSerializer();
        RedisSerializer<String> keyRedisSerializer = new StringRedisSerializer(Charset.forName("UTF-8"));
        template.setKeySerializer(keyRedisSerializer);
        template.setValueSerializer(valueRedisSerializer);
        template.setHashKeySerializer(keyRedisSerializer);
        template.setHashValueSerializer(valueRedisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

    //第三步：定义事务管理bean，就是jdbc里面的transactionManager
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }

}
