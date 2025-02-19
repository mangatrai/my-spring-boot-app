package com.example.entity;

import com.example.deserializer.UUIDDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;

import java.util.UUID;
import java.util.Date;

@Table("auditing_events")
public class YourEntity {

    @PrimaryKeyColumn(name = "event_identifier", type = PrimaryKeyType.PARTITIONED)
    @JsonProperty("event_identifier")
    private String eventIdentifier;

    @PrimaryKeyColumn(name = "event_state", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    @JsonProperty("event_state")
    private String eventState;

    @Column("event_timestamp")
    @JsonProperty("event_timestamp")
    private Date eventTimestamp;

    @Column("event_name")
    @JsonProperty("event_name")
    private String eventName;

    @Column("event_payload_url")
    @JsonProperty("event_payload_url")
    private String eventPayloadUrl;

    @Column("id")
    @JsonDeserialize(using = UUIDDeserializer.class)
    @JsonProperty("id")
    private UUID id;

    // Getters and Setters

    public String getEventIdentifier() {
        return eventIdentifier;
    }

    public void setEventIdentifier(String eventIdentifier) {
        this.eventIdentifier = eventIdentifier;
    }

    public String getEventState() {
        return eventState;
    }

    public void setEventState(String eventState) {
        this.eventState = eventState;
    }

    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Date eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPayloadUrl() {
        return eventPayloadUrl;
    }

    public void setEventPayloadUrl(String eventPayloadUrl) {
        this.eventPayloadUrl = eventPayloadUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
