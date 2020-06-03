package sut.coop.B5910557.CardDataCollectionSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Location;
import sut.coop.B5910557.CardDataCollectionSystem.Entity.Records;
import sut.coop.B5910557.CardDataCollectionSystem.Entity.Student;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.LocationRepository;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.RecordsRepository;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.StudentRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RecordsTests {
    
    private Validator validator;

    @Autowired
    private RecordsRepository recordsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LocationRepository locationRepository;
    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910557_SaveRecordcorrect(){
        Records records = new Records();
        Student student = new Student();
        Location location = locationRepository.findById(1);

        student.setSid("B5913664");
        student.setName("Warunya Chokdee");
        student = studentRepository.saveAndFlush(student);
        records.setStudent(student);
        records.setLocation(location);
        records.setRecordDate(new Date(2020,05,22));

        records = recordsRepository.saveAndFlush(records);
        Optional<Records> found = recordsRepository.findById(records.getId());
        
        assertEquals(student, found.get().getStudent());
        assertEquals(location, found.get().getLocation());
        assertEquals(new Date(2020,05,22), found.get().getRecordDate());
    }

    //----------------------Test Must Not Be Null--------------------------
    @Test
    void B5910557_RecordsDateMustNotBeNull(){
        Records records = new Records();
        Student student = new Student();
        Location location = locationRepository.findById(1);

        student.setSid("B5913664");
        student.setName("Warunya Chokdee");
        student = studentRepository.saveAndFlush(student);
        records.setStudent(student);
        records.setLocation(location);
        records.setRecordDate(null);

        records = recordsRepository.save(records);
        Set<ConstraintViolation<Records>> result = validator.validate(records);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Records> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("recordDate", v.getPropertyPath().toString());
    
    }
}