package com.example.controller;

import com.example.entity.YourEntity;
import com.example.service.YourEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/entities")
public class YourEntityController {

    @Autowired
    private YourEntityService service;

    @PostMapping
    public YourEntity createEntity(@RequestBody YourEntity entity) {
        return service.saveEntity(entity);
    }

    @GetMapping
    public List<YourEntity> getAllEntities() {
        return service.getAllEntities();
    }

    @GetMapping("/{id}")
    public YourEntity getEntityById(@PathVariable("id") UUID id) {
        return service.getEntityById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntity(@PathVariable("id") UUID id) {
        service.deleteEntity(id);
    }

    @GetMapping("/filter")
    public List<YourEntity> filterEntities(
            @RequestParam(required = false) String eventIdentifier,
            @RequestParam(required = false) String eventState,
            @RequestParam(required = false) UUID id) {
        return service.filterEntities(eventIdentifier, eventState, id);
    }
}