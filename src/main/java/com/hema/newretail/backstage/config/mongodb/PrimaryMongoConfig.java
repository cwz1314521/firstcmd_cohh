package com.hema.newretail.backstage.config.mongodb;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author zhanghaisheng
 */
@Configuration
public class PrimaryMongoConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.data.mongodb.primary")
    public MongoProperties primaryMongoProperties() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception {
        return new MongoTemplate(primaryFactory(primaryMongoProperties()), MongoTemplateHelper.customConverter());
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(MongoProperties mongoProperties) throws Exception {
        System.out.println("uri:" + primaryMongoProperties().getUri());
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.readPreference(ReadPreference.secondary());
        options.connectionsPerHost(10);
        return new SimpleMongoDbFactory(new MongoClientURI(primaryMongoProperties().getUri(), options));
    }

}
