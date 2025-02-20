package com.example;

import com.example.entity.YourEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TestDataGenerator implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(TestDataGenerator.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/entities";
    private static final String[] EVENT_STATES = {"VALIDATED", "CREATED", "RESCHEDULED", "CANCELLED", "MODIFIED"};
    private static final Random RANDOM = new Random();
    private static final AtomicInteger eventIdentifierCounter = new AtomicInteger(0);
    String prefix = System.getProperty("instanceName", "TEST");

    @Override
    public void run(String... args) throws Exception {
        boolean generateTestData = Boolean.parseBoolean(System.getProperty("generateTestData", "false"));
        if (generateTestData) {
            logger.info("Instance Name: {}", prefix);
            int numberOfRecords = Integer.parseInt(System.getProperty("numTestRecords", "10000"));
            logger.info("Number of Test Records: {}", numberOfRecords);
            for (int i = 0; i < numberOfRecords; i++) {
                YourEntity entity = generateRandomEntity();
                restTemplate.postForObject(URL, entity, YourEntity.class);
                logger.info("Number of Test Records Inserted: {}", i);
            }
        } else {
            logger.info("Test data generation is disabled.");
        }
    }

    private YourEntity generateRandomEntity() {
        YourEntity entity = new YourEntity();
        entity.setId(UUID.randomUUID());
        entity.setEventIdentifier(prefix + "_MXND_" + eventIdentifierCounter.incrementAndGet());
        entity.setEventTimestamp(new Date());
        entity.setEventState(EVENT_STATES[RANDOM.nextInt(EVENT_STATES.length)]);
        entity.setEventName("com.sephora.happpening.reservation." + entity.getEventState().toLowerCase());
        String datePattern = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'").format(new Date());
        entity.setEventPayloadUrl("https://sepeus1lowerhasm01.blob.core.windows.net/auditing-cloud-event/" + entity.getEventName() + "_" + datePattern);
        return entity;
    }
}