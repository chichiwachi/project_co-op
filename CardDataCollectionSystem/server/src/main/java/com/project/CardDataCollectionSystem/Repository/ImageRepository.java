package com.project.CardDataCollectionSystem.Repository;

import com.project.CardDataCollectionSystem.Entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImageRepository extends JpaRepository<Image, Long>  {
    Image findById(long id);
  
}