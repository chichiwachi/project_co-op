package sut.coop.B5910557.CardDataCollectionSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Location;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.LocationRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class LocationTests {

    private Validator validator;
    
    @Autowired
    private LocationRepository locactionRepository;

    @BeforeEach
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910557_testLocationAllCorrect(){
        Location location = new Location();
        location.setLocation("อาคารเรียนรวม 1");

        location = locactionRepository.saveAndFlush(location);

        Optional<Location> found = locactionRepository.findById(location.getId());
        assertEquals("อาคารเรียนรวม 1", found.get().getLocation());
    }

    @Test
    void B5910557_testLocationNotNull(){
        Location location = new Location();
        location.setLocation(null);

        Set<ConstraintViolation<Location>> result = validator.validate(location);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Location> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("location", v.getPropertyPath().toString());
    }

}