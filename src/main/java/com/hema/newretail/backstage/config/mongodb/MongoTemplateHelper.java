package com.hema.newretail.backstage.config.mongodb;

import com.mongodb.DBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.config.mongodb
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-08 9:57
 */
@Component
public class MongoTemplateHelper implements ApplicationContextAware {
    private final static Logger logger = LoggerFactory.getLogger(MongoTemplateHelper.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (MongoTemplateHelper.applicationContext == null) {
            MongoTemplateHelper.applicationContext = applicationContext;
        }
    }

    /**
     * 获取容器
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取MongoTemplate模板
     *
     * @param source 数据源（你想要获取的数据源）
     * @return MongoTemplate
     */
    public static MongoTemplate getBean(String source) {
        if (StringUtils.isEmpty(source)) {
            logger.error("=========空的source不能获取mongoTemple=========");
            return null;
        }
        String beanName = source + "MongoTemplate";
        return (MongoTemplate) getApplicationContext().getBean(beanName);
    }

    /**
     * @param source 数据源
     * @param name   数据库名称
     * @return MongoCollection
     */
    public static DBCollection getCollection(String source, String name) {
        if (StringUtils.isEmpty(source)) {
            logger.error("=========空的source不能获取mongoTemple=========");
            return null;
        }
        if (StringUtils.isEmpty(name)) {
            logger.error("=========空的表名称不能获取MongoCollection<Document>=========");
            return null;
        }
        String beanName = source + "MongoTemplate";
        MongoTemplate template = (MongoTemplate) getApplicationContext().getBean(beanName);
        return template.getCollection(name);
    }

    public static MappingMongoConverter customConverter() {
        return (MappingMongoConverter) getApplicationContext().getBean("customConverter");
    }
}

