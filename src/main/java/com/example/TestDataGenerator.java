package com.example;

import com.example.entity.YourEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;
import java.util.Date;

@Component
public class TestDataGenerator implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/entities";
    private static final String[] EVENT_STATES = {"VALIDATED", "CREATED", "RESCHEDULED"};
    private static final Random RANDOM = new Random();

    @Override
    public void run(String... args) throws Exception {
        int numberOfRecords = 1000; // Change this to control the number of records
        for (int i = 0; i < numberOfRecords; i++) {
            YourEntity entity = generateRandomEntity();
            restTemplate.postForObject(URL, entity, YourEntity.class);
        }
    }

    private YourEntity generateRandomEntity() {
        YourEntity entity = new YourEntity();
        entity.setId(UUID.randomUUID());
        entity.setEventIdentifier("MXND" + RANDOM.nextInt(10000));
        entity.setEventTimestamp(new Date());
        entity.setEventState(EVENT_STATES[RANDOM.nextInt(EVENT_STATES.length)]);
        entity.setEventName("com.sephora.happpening.reservation.created");
        entity.setEventPayloadUrl("https://sepeus1lowerhasm01.blob.core.windows.net/auditing-cloud-event/com.sephora.happpening.reservation.created_2024-11-01T12%3A15Z");
        entity.setRid(generateRandomRid());
        entity.setSelf("dbs/ZadCAA==/colls/ZadCAJs9qXY=/docs/" + entity.getRid() + "/");
        entity.setEtag(generateRandomEtag());
        entity.setAttachments("attachments/");
        entity.setTs(System.currentTimeMillis() / 1000L);
        return entity;
    }

    private String generateRandomRid() {
        byte[] array = new byte[16]; // length is bounded by 16
        RANDOM.nextBytes(array);
        return java.util.Base64.getEncoder().encodeToString(array);
    }

    private String generateRandomEtag() {
        return String.format("\"%08x-0000-0100-0000-%08x\"", RANDOM.nextInt(), RANDOM.nextInt());
    }
}