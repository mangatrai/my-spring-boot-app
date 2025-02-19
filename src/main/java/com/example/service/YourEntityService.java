package com.example.service;

import com.example.entity.YourEntity;
import com.example.repository.YourEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YourEntityService {

    @Autowired
    private YourEntityRepository repository;

    public YourEntity saveEntity(YourEntity entity) {
        return repository.save(entity);
    }

    public YourEntity updateEntityByEventIdentifier(String eventIdentifier, YourEntity entity) {
        List<YourEntity> existingEntities = repository.findByEventIdentifier(eventIdentifier);
        if (!existingEntities.isEmpty()) {
            YourEntity existingEntity = existingEntities.get(0);
            entity.setEventIdentifier(existingEntity.getEventIdentifier());
            return repository.save(entity);
        }
        return null;
    }

    public YourEntity updateEntityByEventIdentifierAndEventState(String eventIdentifier, String eventState, YourEntity entity) {
        List<YourEntity> existingEntities = repository.findByEventIdentifierAndEventState(eventIdentifier, eventState);
        if (!existingEntities.isEmpty()) {
            YourEntity existingEntity = existingEntities.get(0);
            entity.setEventIdentifier(existingEntity.getEventIdentifier());
            entity.setEventState(existingEntity.getEventState());
            return repository.save(entity);
        }
        return null;
    }

    public List<YourEntity> getAllEntities() {
        return repository.findAll();
    }

    public List<YourEntity> getEntitiesByEventIdentifier(String eventIdentifier) {
        return repository.findByEventIdentifier(eventIdentifier);
    }

    public List<YourEntity> getEntitiesByEventIdentifierAndEventState(String eventIdentifier, String eventState) {
        return repository.findByEventIdentifierAndEventState(eventIdentifier, eventState);
    }

    public void deleteEntityByEventIdentifierAndEventState(String eventIdentifier, String eventState) {
        repository.deleteByEventIdentifierAndEventState(eventIdentifier, eventState);
    }

    public void deleteEntityByEventIdentifier(String eventIdentifier) {
        repository.deleteByEventIdentifier(eventIdentifier);
    }

    public List<YourEntity> filterEntities(String eventIdentifier, String eventState, String eventName) {
        if (eventIdentifier != null && eventState != null && eventName != null) {
            return repository.findByEventIdentifierAndEventStateAndEventName(eventIdentifier, eventState, eventName);
        } else if (eventIdentifier != null && eventState != null) {
            return repository.findByEventIdentifierAndEventState(eventIdentifier, eventState);
        } else if (eventIdentifier != null && eventName != null) {
            return repository.findByEventIdentifierAndEventName(eventIdentifier, eventName);
        } else if (eventState != null && eventName != null) {
            return repository.findByEventStateAndEventName(eventState, eventName);
        } else if (eventIdentifier != null) {
            return repository.findByEventIdentifier(eventIdentifier);
        } else if (eventState != null) {
            return repository.findByEventState(eventState);
        } else if (eventName != null) {
            return repository.findByEventName(eventName);
        } else {
            return repository.findAll();
        }
    }
}