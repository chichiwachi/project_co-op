package sut.coop.B5910557.CardDataCollectionSystem.Controller;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Location;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.LocationRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class LocationController {
    @Autowired
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/location")
    public Collection<Location> locations() {
        return locationRepository.findAll().stream().collect(Collectors.toList());
    }
}