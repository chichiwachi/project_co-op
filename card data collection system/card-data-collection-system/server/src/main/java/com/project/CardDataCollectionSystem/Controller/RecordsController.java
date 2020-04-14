package com.project.CardDataCollectionSystem.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.CardDataCollectionSystem.Entity.Records;
import com.project.CardDataCollectionSystem.Repository.RecordsRepository;
//import com.project.CardDataCollectionSystem.Entity.Student;
//import com.project.CardDataCollectionSystem.Repository.StudentRepository;
import com.project.CardDataCollectionSystem.Entity.Location;
import com.project.CardDataCollectionSystem.Repository.LocationRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class RecordsController {
    @Autowired 
    private final RecordsRepository recordsRepository;
    // @Autowired 
    // private StudentRepository studentRepository;
    @Autowired 
    private LocationRepository  locationRepository;

    RecordsController(  RecordsRepository recordsRepository,
                        //StudentRepository studentRepository,
                        LocationRepository  locationRepository) {
        this.recordsRepository = recordsRepository;
        //this.studentRepository = studentRepository;
        this.locationRepository = locationRepository;
    }
    
    @GetMapping("/records")
    public List<Records> getRecords() {
        return recordsRepository.findAll();
    }

    @GetMapping("/records/{id}")
    public Records getRecord(@PathVariable long id) {
        return recordsRepository.findById(id);
    }

    @PostMapping("/records/{locatin_id}")
    public Records newRepair( Records newRecords,
                        @PathVariable long location_id) {
                            
        //Student student = studentRepository.findById(student_id);
        Location location = locationRepository.findById(location_id);
        
        newRecords.setLocation(location);
        newRecords.setRecordDate(new Date());

        return recordsRepository.save(newRecords);
    }
}