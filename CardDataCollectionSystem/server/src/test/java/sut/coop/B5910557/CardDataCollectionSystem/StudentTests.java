package sut.coop.B5910557.CardDataCollectionSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Student;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.StudentRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class StudentTests {
    
    private Validator validator;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910557_testSaveStudentCorrect(){
        Student student = new Student();
        
        student.setSid("B5913664");
        student.setName("Warunya Chokdee");

        student = studentRepository.saveAndFlush(student);
        Optional<Student> found = studentRepository.findById(student.getId());
        assertEquals("B5913664", found.get().getSid());
        assertEquals("Warunya Chokdee", found.get().getName());
    }

    //--------------------------Test Must Not Be Null-------------------------------
    @Test
    void B5910557_testStudentIdMustNotBeNull(){
        Student student = new Student();

        student.setSid(null);
        student.setName("Warunya Chokdee");

        student = studentRepository.save(student);
        Set<ConstraintViolation<Student>> result = validator.validate(student);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("sid", v.getPropertyPath().toString());
    }

    @Test
    void B5910557_testStudentNameMustNotBeNull(){
        Student student = new Student();

        student.setSid("B5913664");
        student.setName(null);

        student = studentRepository.save(student);
        Set<ConstraintViolation<Student>> result = validator.validate(student);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    //--------------------------Test Unique-------------------------
    @Test
    void B5910557_testStudentIdMustBeUnique(){
        Student student1 = new Student();

        try{
            student1.setSid("B5913664");
            student1.setName("Warunya Chokdee");
            student1 = studentRepository.save(student1);
        } catch (DataIntegrityViolationException e) {
            assertThrows(DataIntegrityViolationException.class, () ->{
                Student student2 = new Student();

                student2.setSid("B5913664");
                student2.setName("Warunya Chokdee");
                student2 = studentRepository.save(student2);
            });
        } 
    }

    //--------------------------Test Size Min 5 Max 30------------------------
    @Test
    void B5910557_testStudentNameSizeMustNotLessThen5(){
        Student student = new Student();

        student.setSid("B5913664");
        student.setName("Waru");

        student = studentRepository.save(student);
        Set<ConstraintViolation<Student>> result = validator.validate(student);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void B5910557_testStudentNameSizeMustNotMoreThen30(){
        Student student = new Student();

        student.setSid("B5913664");
        student.setName("Warunya Chokdeeeeeeeeeeeeeeeeee");

        student = studentRepository.save(student);
        Set<ConstraintViolation<Student>> result = validator.validate(student);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    //-----------------------Test Pattern--------------------
    @Test
    void B5910557_testStudentNamePatternMustNotHaveDigit(){
        Student student = new Student();

        student.setSid("B5913664");
        student.setName("Warunya Chokdee888");

        student = studentRepository.save(student);
        Set<ConstraintViolation<Student>> result = validator.validate(student);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must match \"\\D*\"", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}