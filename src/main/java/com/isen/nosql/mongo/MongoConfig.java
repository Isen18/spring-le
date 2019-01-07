package com.isen.nosql.mongo;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Isen
 * @date 2018/12/29 9:48
 * @since 1.0
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.isen.nosql.orders.db")
public class MongoConfig {
//    @Bean
//    public MongoFactoryBean mongo(){
//        MongoFactoryBean mongoFactoryBean = new MongoFactoryBean();
//        mongoFactoryBean.setHost("localhost");
//        return mongoFactoryBean;
//    }

    @Bean
    public MongoClient mongo(){
        return new MongoClient("localhost");
    }

    @Bean
    public MongoOperations mongoTemplate(MongoClient mongoClient){
        return new MongoTemplate(mongoClient, "OrderDB");
    }
}
