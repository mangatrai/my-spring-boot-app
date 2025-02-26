package com.example;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.cql.CqlTemplate;

import com.datastax.oss.driver.api.core.CqlSession;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class MySpringBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootAppApplication.class, args);
    }

    @Bean
    public CqlTemplate cqlTemplate(CqlSession cqlSession) {
        return new CqlTemplate(cqlSession);
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        final Path bundle;
        try {
            bundle = Paths.get(astraProperties.getSecureConnectBundle().getURI());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get secure connect bundle", e);
        }
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
}