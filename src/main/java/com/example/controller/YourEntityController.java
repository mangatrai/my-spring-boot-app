package com.example.controller;

import com.example.entity.YourEntity;
import com.example.service.YourEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entities")
public class YourEntityController {

    @Autowired
    private YourEntityService service;

    @PostMapping
    public ResponseEntity<YourEntity> createEntity(@RequestBody YourEntity entity) {
        YourEntity createdEntity = service.saveEntity(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{eventIdentifier}")
    public ResponseEntity<String> updateEntityByEventIdentifier(@PathVariable("eventIdentifier") String eventIdentifier, @RequestBody YourEntity entity) {
        return new ResponseEntity<>("Update not allowed on eventIdentifier only", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{eventIdentifier}/{eventState}")
    public ResponseEntity<YourEntity> updateEntityByEventIdentifierAndEventState(@PathVariable("eventIdentifier") String eventIdentifier, @PathVariable("eventState") String eventState, @RequestBody YourEntity entity) {
        YourEntity updatedEntity = service.updateEntityByEventIdentifierAndEventState(eventIdentifier, eventState, entity);
        if (updatedEntity != null) {
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<YourEntity>> getAllEntities() {
        List<YourEntity> entities = service.getAllEntities();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{eventIdentifier}")
    public ResponseEntity<List<YourEntity>> getEntitiesByEventIdentifier(@PathVariable("eventIdentifier") String eventIdentifier) {
        List<YourEntity> entities = service.getEntitiesByEventIdentifier(eventIdentifier);
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{eventIdentifier}/{eventState}")
    public ResponseEntity<List<YourEntity>> getEntitiesByEventIdentifierAndEventState(@PathVariable("eventIdentifier") String eventIdentifier, @PathVariable("eventState") String eventState) {
        List<YourEntity> entities = service.getEntitiesByEventIdentifierAndEventState(eventIdentifier, eventState);
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @DeleteMapping("/{eventIdentifier}/{eventState}")
    public ResponseEntity<Void> deleteEntityByEventIdentifierAndEventState(@PathVariable("eventIdentifier") String eventIdentifier, @PathVariable("eventState") String eventState) {
        service.deleteEntityByEventIdentifierAndEventState(eventIdentifier, eventState);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{eventIdentifier}")
    public ResponseEntity<Void> deleteEntityByEventIdentifier(@PathVariable("eventIdentifier") String eventIdentifier) {
        service.deleteEntityByEventIdentifier(eventIdentifier);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<YourEntity>> filterEntities(
            @RequestParam(required = false) String eventIdentifier,
            @RequestParam(required = false) String eventState,
            @RequestParam(required = false) String eventName) {
        List<YourEntity> entities = service.filterEntities(eventIdentifier, eventState, eventName);
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
