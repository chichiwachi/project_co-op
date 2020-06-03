// package sut.coop.B5910557.CardDataCollectionSystem.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RestController;

// import sut.coop.B5910557.CardDataCollectionSystem.Entity.Location;
// import sut.coop.B5910557.CardDataCollectionSystem.Entity.Records;
// import sut.coop.B5910557.CardDataCollectionSystem.Entity.Student;
// import sut.coop.B5910557.CardDataCollectionSystem.Repository.LocationRepository;
// import sut.coop.B5910557.CardDataCollectionSystem.Repository.RecordsRepository;
// import sut.coop.B5910557.CardDataCollectionSystem.Repository.StudentRepository;
// import sut.coop.B5910557.CardDataCollectionSystem.Utils.CsvUtils;

// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.time.LocalDate;
// import java.time.ZoneId;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Date;
// import java.util.List;

// @CrossOrigin(origins = "http://localhost:8080")
// @RestController

// public class DownloadController {

//     @Autowired 
//     private final StudentRepository studentRepository;

//     @Autowired 
//     private final RecordsRepository recordsRepository;

//     @Autowired 
//     private final LocationRepository  locationRepository;

//     DownloadController( final RecordsRepository recordsRepository,
//                         final StudentRepository studentRepository,
//                         final LocationRepository  locationRepository) {
//         this.recordsRepository = recordsRepository;
//         this.studentRepository = studentRepository;
//         this.locationRepository = locationRepository;
//     }
    
//     @GetMapping("/download/student.csv")
//     public void downloadCsv(final HttpServletResponse response) throws IOException {
//         response.setContentType("text/csv");
//         response.setHeader("Content-Disposition", "attachment; file=student.csv");
//         CsvUtils.downloadCsv(response.getWriter(), getAll()) ;
//     }
    
//     public List<Student> getAll() {
//         final List<Student> students = new ArrayList<>();
//         studentRepository.findAll().forEach(student -> students.add(student));
//         return students;
//     }

//     @GetMapping("/download/records.csv")
//     public void downloadRecordsCsv(final HttpServletResponse response) throws IOException {
//         response.setContentType("text/csv");
//         response.setHeader("Content-Disposition", "attachment; file=records.csv");
//         CsvUtils.downloadRecordsCsv(response.getWriter(), getRecordAll()) ;
//     }
    
//     public List<Records> getRecordAll() {
//         final List<Records> records = new ArrayList<>();
//         recordsRepository.findAll().forEach(record -> records.add(record));
//         return records;
//     }

//     @GetMapping("/download/location.csv")
//     public void downloadLocationCsv(final HttpServletResponse response) throws IOException {
//         response.setContentType("text/csv");
//         response.setHeader("Content-Disposition", "attachment; file=location.csv");
//         CsvUtils.downloadLocationCsv(response.getWriter(), getLocationAll()) ;
//     }
    
//     public Collection<Location> getLocationAll() {
//         final Collection<Location> locations = new ArrayList<>();
//         locationRepository.findAll().forEach(location -> locations.add(location));
//         return locations;
//     }


//     //------------------------------------------Download by SearchRecords----------------------------------------------
    
//     //recordDate
//     @GetMapping("/download/recordsbydate.csv")
//     public void downloadRecordsCsvByDate(final HttpServletResponse response, String recordDate) throws IOException {
//         response.setContentType("text/csv");
//         response.setHeader("Content-Disposition", "attachment; file=records.csv");
//         CsvUtils.downloadRecordsCsvByDate(response.getWriter(), getRecordDate(recordDate));       
//     }
    
//     @GetMapping("/recorddate/{recordDate}")
//     public Collection<Records> getRecordDate(@PathVariable final String recordDate) {
//         ZoneId defaultZoneId = ZoneId.systemDefault();
//         LocalDate stratDate = LocalDate.parse(recordDate);
//         LocalDate endDate = LocalDate.parse(recordDate);
//         Date NewStratDate = Date.from(stratDate.atStartOfDay(defaultZoneId).toInstant());
//         Date NewEndDate = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());

//         System.out.println(NewStratDate);
//         System.out.println(NewEndDate);
//         return this.recordsRepository.findByRecordDate(NewStratDate, NewEndDate);
//     }

//     //Student ID
//     @GetMapping("/download/recordsbyid.csv")
//     public void downloadRecordsCsvById(final HttpServletResponse response, String student) throws IOException {
//         response.setContentType("text/csv");
//         response.setHeader("Content-Disposition", "attachment; file=records.csv");
//         CsvUtils.downloadRecordsCsvById(response.getWriter(), findByStudent(student)) ;      
//     }
    
//     @GetMapping("/recordstudent/{student}")
//     public Collection<Records> findByStudent(@PathVariable String student) {
//         return recordsRepository.findByStudent(student);
//     }

//     //Student name
//     @GetMapping("/download/recordsbyname.csv")
//     public void downloadRecordsCsvByName(final HttpServletResponse response, String studentname) throws IOException {
//         response.setContentType("text/csv");
//         response.setHeader("Content-Disposition", "attachment; file=records.csv");
//         CsvUtils.downloadRecordsCsvByName(response.getWriter(), findByStudentName(studentname)); 
//     }
    
//     @GetMapping("/recordstudentname/{studentname}")
//     public Collection<Records> findByStudentName(@PathVariable String studentname) {
//         return recordsRepository.findByStudentName(studentname);
//     }

//     //Location
//     @GetMapping("/download/recordsbylocation.csv")
//     public void downloadRecordsCsvByLocation(final HttpServletResponse response, long location) throws IOException {
//         response.setContentType("text/csv");
//         response.setHeader("Content-Disposition", "attachment; file=records.csv");
//         CsvUtils.downloadRecordsCsvByLocation(response.getWriter(), findByLocation(location));   
//     }
    
//     @GetMapping("/recordlocation/{location}")
//     public Collection<Records> findByLocation(@PathVariable long location) {
//         return recordsRepository.findByLocation(location);
//     }
//     //-------------------------------------------------------------------------------------------------------------------------
// }