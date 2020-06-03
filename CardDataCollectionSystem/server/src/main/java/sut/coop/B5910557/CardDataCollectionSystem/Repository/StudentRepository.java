package sut.coop.B5910557.CardDataCollectionSystem.Repository;
import sut.coop.B5910557.CardDataCollectionSystem.Entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long>  {
    Student findById(long id);
    Student findBySid(String sid);
    Student findByName(String name);
}