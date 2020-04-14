package com.project.CardDataCollectionSystem.Repository;

import com.project.CardDataCollectionSystem.Entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long>  {
    Student findByIdS(String id);
    Student findByName(String name);
}