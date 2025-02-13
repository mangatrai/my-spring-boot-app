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

    @PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @JsonDeserialize(using = UUIDDeserializer.class)
    @JsonProperty("id")
    private UUID id;

    @Column("event_timestamp")
    @JsonProperty("event_timestamp")
    private Date eventTimestamp;

    @Column("event_name")
    @JsonProperty("event_name")
    private String eventName;

    @Column("event_payload_url")
    @JsonProperty("event_payload_url")
    private String eventPayloadUrl;

    @Column("rid")
    @JsonProperty("rid")
    private String rid;

    @Column("self")
    @JsonProperty("self")
    private String self;

    @Column("etag")
    @JsonProperty("etag")
    private String etag;

    @Column("attachments")
    @JsonProperty("attachments")
    private String attachments;

    @Column("ts")
    @JsonProperty("ts")
    private Long ts;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
