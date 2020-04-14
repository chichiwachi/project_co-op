package com.project.CardDataCollectionSystem.Repository;

import com.project.CardDataCollectionSystem.Entity.Records;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecordsRepository extends JpaRepository<Records, Long>  {
    Records findById(long id);
}