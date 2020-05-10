package com.mosman.tutorfinderapp.controlles;

import com.mosman.tutorfinderapp.repos.CourseRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/*@ExtendWith(SpringExtension.class)
@DataJpaTest
class TestControllerTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepo courseRepo;


    @Test
    public void testData(){
        System.out.println(courseRepo.count());
        assertEquals(courseRepo.count(), 3);
    }

}*/
