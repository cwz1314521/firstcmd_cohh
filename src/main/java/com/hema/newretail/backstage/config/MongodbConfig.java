package com.hema.newretail.backstage.config;

import com.hema.newretail.backstage.common.mongodb.BigDecimalToDecimal128Converter;
import com.hema.newretail.backstage.common.mongodb.Decimal128ToBigDecimalConverter;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.config
 * BigDecimal和Decimal128的转换
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-24 11:50
 */
@Configuration
public class MongodbConfig extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Mongo(host, port);
    }

    @Bean
    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        List<Object> list = new ArrayList<>();
        list.add(new BigDecimalToDecimal128Converter());
        list.add(new Decimal128ToBigDecimalConverter());
        converter.setCustomConversions(new CustomConversions(list));
        return converter;
    }

}