package com.project.CardDataCollectionSystem.Controller;

import com.project.CardDataCollectionSystem.Entity.Time;
import com.project.CardDataCollectionSystem.Repository.TimeRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class TimeController {
    @Autowired
    private final TimeRepository timeRepository;

    public TimeController(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @GetMapping("/time")
    public Collection<Time> times() {
        return timeRepository.findAll().stream().collect(Collectors.toList());
    }
}