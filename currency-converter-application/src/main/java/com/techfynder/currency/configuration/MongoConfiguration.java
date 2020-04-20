package com.techfynder.currency.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

@Configuration
public class MongoConfiguration {

	private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "currency_db";
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }
	
//	@Bean
//	public MongoDbFactory mongoDbFactory() throws Exception {
//		return new SimpleMongoDbFactory(new MongoClient(), "currency");
//	}
//
//	 @Bean
//	 public MongoTemplate mongoTemplate() throws Exception {
//		
//		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
//				
//		return mongoTemplate;
//		
//	}
	
}
