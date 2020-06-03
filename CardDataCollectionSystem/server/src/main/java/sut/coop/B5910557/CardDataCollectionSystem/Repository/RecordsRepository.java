package sut.coop.B5910557.CardDataCollectionSystem.Repository;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Records;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecordsRepository extends JpaRepository<Records, Long>  {
    Records findById(long id);
    
    @Query(value = "SELECT r FROM Records r WHERE r.student.name = :student_name")
    Collection<Records> findByStudentName(@Param("student_name") String student_name);
    
    @Query(value = "SELECT r FROM Records r WHERE r.student.sid = :student_id")
    Collection<Records> findByStudent(@Param("student_id") String student_id);

    @Query(value = "SELECT * FROM RECORDS AS r WHERE r.LOCATION_ID = :location_id"
    , nativeQuery = true)
    Collection<Records> findByLocation(@Param("location_id") long location_id);

    @Query(value = "SELECT r FROM Records r WHERE r.recordDate BETWEEN :date AND :endDate")
    Collection<Records> findByRecordDate(@Param("date") Date date, Date endDate);

    
}