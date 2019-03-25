package com.hema.newretail.backstage.config.mongodb;

import com.hema.newretail.backstage.common.mongodb.BigDecimalToDecimal128Converter;
import com.hema.newretail.backstage.common.mongodb.Decimal128ToBigDecimalConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.config.mongodb
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-08 10:00
 */
@Configuration
public class MongoCustomConverter {
    @Bean(name = "customConverter")
    public MappingMongoConverter customConversions(MongoDbFactory factory, MongoMappingContext context) {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, context);
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new BigDecimalToDecimal128Converter());
        converterList.add(new Decimal128ToBigDecimalConverter());
        converter.setCustomConversions(new CustomConversions(converterList));
        return converter;
    }
}

