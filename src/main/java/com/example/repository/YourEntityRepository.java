package com.example.repository;

import com.example.entity.YourEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface YourEntityRepository extends CassandraRepository<YourEntity, UUID> {

    List<YourEntity> findByEventIdentifierAndEventStateAndId(String eventIdentifier, String eventState, UUID id);

    List<YourEntity> findByEventIdentifierAndEventState(String eventIdentifier, String eventState);

    List<YourEntity> findByEventIdentifierAndId(String eventIdentifier, UUID id);

    List<YourEntity> findByEventStateAndId(String eventState, UUID id);

    List<YourEntity> findByEventIdentifier(String eventIdentifier);

    List<YourEntity> findByEventState(String eventState);
}