package sut.coop.B5910557.CardDataCollectionSystem.Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Records;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.RecordsRepository;
import sut.coop.B5910557.CardDataCollectionSystem.Entity.Student;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.StudentRepository;
import sut.coop.B5910557.CardDataCollectionSystem.Utils.CsvUtils;
import sut.coop.B5910557.CardDataCollectionSystem.Entity.Location;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.LocationRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class RecordsController {
    @Autowired 
    private final RecordsRepository recordsRepository;

    @Autowired 
    private final StudentRepository studentRepository;

    @Autowired 
    private final LocationRepository  locationRepository;

    RecordsController(  final RecordsRepository recordsRepository,
                        final StudentRepository studentRepository,
                        final LocationRepository  locationRepository) {
        this.recordsRepository = recordsRepository;
        this.studentRepository = studentRepository;
        this.locationRepository = locationRepository;
    }
    
    @GetMapping("/records")
    public List<Records> getRecords() {
        return recordsRepository.findAll();
    }

    @GetMapping("/records/{id}")
    public Records getRecord(@PathVariable final long id) {
        return recordsRepository.findById(id);
    }

    @GetMapping("/recordlocation/{location}")
    public Collection<Records> findByLocation(@PathVariable long location) {
        return recordsRepository.findByLocation(location);
    }

    @GetMapping("/recordstudent/{student}")
    public Collection<Records> findByStudent(@PathVariable String student) {
        return recordsRepository.findByStudent(student);
    }

    @GetMapping("/recordstudentname/{studentname}")
    public Collection<Records> findByStudentName(@PathVariable String studentname) {
        return recordsRepository.findByStudentName(studentname);
    }

    @GetMapping("/recorddate/{recordDate}")
    public Collection<Records> getRecordDate(@PathVariable final String recordDate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate stratDate = LocalDate.parse(recordDate);
        LocalDate endDate = LocalDate.parse(recordDate);
        Date NewStratDate = Date.from(stratDate.atStartOfDay(defaultZoneId).toInstant());
        Date NewEndDate = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());

        System.out.println(NewStratDate);
        System.out.println(NewEndDate);
        return this.recordsRepository.findByRecordDate(NewStratDate, NewEndDate);
    }

    //------------------------------------------Download by SearchRecords----------------------------------------------
    //recordDate
    @GetMapping("/download/recordsbydate.csv")
    public void downloadRecordsCsvByDate(final HttpServletResponse response,final String recordDate) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=records.csv");
        CsvUtils.downloadRecordsCsvByDate(response.getWriter(), getRecordDate(recordDate));       
    }

    //Student ID
    @GetMapping("/download/recordsbyid.csv")
    public void downloadRecordsCsvById(final HttpServletResponse response, String student) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=records.csv");
        CsvUtils.downloadRecordsCsvById(response.getWriter(), findByStudent(student)) ;      
    }

    //Student name
    @GetMapping("/download/recordsbyname.csv")
    public void downloadRecordsCsvByName(final HttpServletResponse response, String studentname) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=records.csv");
        CsvUtils.downloadRecordsCsvByName(response.getWriter(), findByStudentName(studentname)); 
    }
    
    //Location
    @GetMapping("/download/recordsbylocation.csv")
    public void downloadRecordsCsvByLocation(final HttpServletResponse response, long location) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=records.csv");
        CsvUtils.downloadRecordsCsvByLocation(response.getWriter(), findByLocation(location));   
    }
    //-------------------------------------------------------------------------------------------------------------------------

    @PostMapping("/records/{student_id}/{location_id}")
    public Records newRecords( Records newRecords,
        @PathVariable long student_id,
        @PathVariable long location_id) {
                            
        Student student = studentRepository.findById(student_id);
        Location location = locationRepository.findById(location_id);

        newRecords.setStudent(student);
        newRecords.setLocation(location);
        newRecords.setRecordDate(new Date());

        return recordsRepository.save(newRecords);
    }
}