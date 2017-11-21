package com.datastech.transactionsheduler.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ivan
 */
@Configuration
public class MongoDBConfig {
    
    @Value("${mongodb.host}")
    private String host;
    
    @Value("${mongodb.port}")
    private int port;

    @Bean
    public MongoClient getMongoClient() {
        MongoClient client = new MongoClient(host, port);
        return client;
    }

}
