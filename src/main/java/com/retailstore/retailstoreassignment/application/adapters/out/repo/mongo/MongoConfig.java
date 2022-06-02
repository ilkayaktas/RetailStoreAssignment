package com.retailstore.retailstoreassignment.application.adapters.out.repo.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfig extends AbstractMongoClientConfiguration {

   @Autowired
   MongoProperties mongoProperties;

   @Bean
   MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
      return new MongoTransactionManager(dbFactory);
   }

   @Bean
   public MongoClient mongo() {
      ConnectionString connectionString = new ConnectionString(mongoProperties.getUri());
      MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
              .applyConnectionString(connectionString)
              .build();

      return MongoClients.create(mongoClientSettings);
   }

   @Bean
   public MongoTemplate mongoTemplate(){
      return new MongoTemplate(mongo(), mongoProperties.getDatabase());
   }

   @Override
   protected String getDatabaseName() {
      return mongoProperties.getDatabase();
   }

}
