package sut.coop.B5910557.CardDataCollectionSystem.Utils;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Records;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.LocationRepository;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.RecordsRepository;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.StudentRepository;
import sut.coop.B5910557.CardDataCollectionSystem.Entity.Student;
import sut.coop.B5910557.CardDataCollectionSystem.Entity.Location;

public class CsvUtils {

    @Autowired 
    private final RecordsRepository recordsRepository;

    @Autowired 
    private final StudentRepository studentRepository;

    @Autowired 
    private final LocationRepository  locationRepository;

    CsvUtils(   final RecordsRepository recordsRepository,
                final StudentRepository studentRepository,
                final LocationRepository  locationRepository) {
        this.recordsRepository = recordsRepository;
        this.studentRepository = studentRepository;
        this.locationRepository = locationRepository;
    }
    
    public static void downloadCsv(final PrintWriter writer, final List<Student> students) {
        writer.write("ID, Student ID, Name \n");
        for (final Student student : students) {
            writer.write(student.getId() + "," + student.getSid() + "," + student.getName() + "\n");
        }
    }

    public static void downloadRecordsCsv(final PrintWriter writer, final List<Records> records) {
        writer.write("ID, recordDate , Student ID, Student Name, Location \n");
        for (final Records record : records) {
            writer.write(record.getId() + "," + record.getRecordDate() + "," + record.getStudent().getSid() + "," + record.getStudent().getName() + "," + record.getLocation().getLocation() + "\n");
        }
    }

    public static void downloadLocationCsv(final PrintWriter writer, final Collection<Location> locations) {
        writer.write("ID, Location \n");
        for (final Location location : locations) {
            writer.write(location.getId() + "," + location.getLocation() + "\n");
        }
    }

    //Download by SearchRecords
    public static void downloadRecordsCsvByLocation(final PrintWriter writer, final Collection<Records> records) {
        writer.write("ID, recordDate , Student ID, Student Name, Location \n");
        for (final Records record : records) {
                writer.write(record.getId() + "," + record.getRecordDate() + "," + record.getStudent().getSid() + "," + record.getStudent().getName() + "," + record.getLocation().getLocation() + "\n"); 
        }        
    }

    public static void downloadRecordsCsvById(final PrintWriter writer, final Collection<Records> records) {
        writer.write("ID, recordDate , Student ID, Student Name, Location \n");
        for (final Records record : records) {
            writer.write(record.getId() + "," + record.getRecordDate() + "," + record.getStudent().getSid() + "," + record.getStudent().getName() + "," + record.getLocation().getLocation() + "\n");
        }
    }

    public static void downloadRecordsCsvByName(final PrintWriter writer, final Collection<Records> records) {
        writer.write("ID, recordDate , Student ID, Student Name, Location \n");
        for (final Records record : records) {
            writer.write(record.getId() + "," + record.getRecordDate() + "," + record.getStudent().getSid() + "," + record.getStudent().getName() + "," + record.getLocation().getLocation() + "\n");
        }
    }

    public static void downloadRecordsCsvByDate(final PrintWriter writer, final Collection<Records> records) {
        writer.write("ID, recordDate , Student ID, Student Name, Location \n");
        for (final Records record : records) {
            writer.write(record.getId() + "," + record.getRecordDate() + "," + record.getStudent().getSid() + "," + record.getStudent().getName() + "," + record.getLocation().getLocation() + "\n");
        }
    }


}