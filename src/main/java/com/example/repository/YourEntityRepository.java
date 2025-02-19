package com.example.repository;

import com.example.entity.YourEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YourEntityRepository extends CassandraRepository<YourEntity, String> {

    List<YourEntity> findByEventIdentifier(String eventIdentifier);

    void deleteByEventIdentifierAndEventState(String eventIdentifier, String eventState);

    void deleteByEventIdentifier(String eventIdentifier);

    List<YourEntity> findByEventIdentifierAndEventState(String eventIdentifier, String eventState);

    List<YourEntity> findByEventIdentifierAndEventName(String eventIdentifier, String eventName);

    List<YourEntity> findByEventStateAndEventName(String eventState, String eventName);

    List<YourEntity> findByEventIdentifierAndEventStateAndEventName(String eventIdentifier, String eventState, String eventName);

    List<YourEntity> findByEventState(String eventState);

    List<YourEntity> findByEventName(String eventName);
}