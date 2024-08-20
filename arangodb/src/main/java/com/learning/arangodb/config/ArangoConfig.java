package com.learning.arangodb.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com.learning.arangodb.character"})
public class ArangoConfig implements com.arangodb.springframework.config.ArangoConfiguration {

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder().host("localhost", 8529).user("root").password("rootpassword");
    }

    @Override
    public String database() {
        return "spring-demo";
    }
}
