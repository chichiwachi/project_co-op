package com.project.CardDataCollectionSystem.Repository;

import com.project.CardDataCollectionSystem.Entity.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LocationRepository extends JpaRepository<Location, Long>  {
    Location findById(long id);
    Location findByLocation(String location);
}