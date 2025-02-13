package com.example.service;

import com.example.entity.YourEntity;
import com.example.repository.YourEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class YourEntityService {

    @Autowired
    private YourEntityRepository repository;

    public YourEntity saveEntity(YourEntity entity) {
        return repository.save(entity);
    }

    public List<YourEntity> getAllEntities() {
        return repository.findAll();
    }

    public YourEntity getEntityById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteEntity(UUID id) {
        repository.deleteById(id);
    }

    public List<YourEntity> filterEntities(String eventIdentifier, String eventState, UUID id) {
        if (eventIdentifier != null && eventState != null && id != null) {
            return repository.findByEventIdentifierAndEventStateAndId(eventIdentifier, eventState, id);
        } else if (eventIdentifier != null && eventState != null) {
            return repository.findByEventIdentifierAndEventState(eventIdentifier, eventState);
        } else if (eventIdentifier != null && id != null) {
            return repository.findByEventIdentifierAndId(eventIdentifier, id);
        } else if (eventState != null && id != null) {
            return repository.findByEventStateAndId(eventState, id);
        } else if (eventIdentifier != null) {
            return repository.findByEventIdentifier(eventIdentifier);
        } else if (eventState != null) {
            return repository.findByEventState(eventState);
        } else if (id != null) {
            return repository.findById(id)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        } else {
            return repository.findAll();
        }
    }
}