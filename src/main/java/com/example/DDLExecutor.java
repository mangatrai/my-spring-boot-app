package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DDLExecutor implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DDLExecutor.class);

    @Autowired
    private CqlTemplate cqlTemplate;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS auditing_events (" +
            "event_identifier text," +
            "event_timestamp timestamp," +
            "event_state text," +
            "event_name text," +
            "event_payload_url text," +
            "id uuid," +
            "PRIMARY KEY ((event_identifier), event_state)" +
            ");";

    private static final String CREATE_INDEX_EVENT_STATE = "CREATE CUSTOM INDEX IF NOT EXISTS ON auditing_events (event_state) USING 'StorageAttachedIndex';";
    private static final String CREATE_INDEX_EVENT_NAME = "CREATE CUSTOM INDEX IF NOT EXISTS ON auditing_events (event_name) USING 'StorageAttachedIndex';";

    @Override
    public void run(String... args) throws Exception {
        try {
            cqlTemplate.execute(CREATE_TABLE);
            logger.info("Table 'auditing_events' created successfully or already exists.");

            cqlTemplate.execute(CREATE_INDEX_EVENT_STATE);
            logger.info("Index on 'event_state' created successfully or already exists.");

            cqlTemplate.execute(CREATE_INDEX_EVENT_NAME);
            logger.info("Index on 'event_name' created successfully or already exists.");
        } catch (Exception e) {
            logger.error("Error creating table or indexes: ", e);
        }
    }
}