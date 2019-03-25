package com.hema.newretail.backstage.config.mongodb;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author ZhangHaiSheng
 */
@Configuration
public class SecondaryMongoConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
    public MongoProperties secondaryMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryFactory(secondaryMongoProperties()), MongoTemplateHelper.customConverter());
    }

    @Bean
    public MongoDbFactory secondaryFactory(MongoProperties mongoProperties) throws Exception {
        System.out.println("uri:" + secondaryMongoProperties().getUri());
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.readPreference(ReadPreference.secondary());
        options.connectionsPerHost(10);
        return new SimpleMongoDbFactory(new MongoClientURI(secondaryMongoProperties().getUri(), options));
    }
}