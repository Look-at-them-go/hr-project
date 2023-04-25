package com.dragan.project.repo;

import com.dragan.project.models.Candidate;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CandidateRepoTest {

    @Autowired
    private CandidateRepo candidateRepo;

    @Test
    @Order(1)
    void createCandidateTest(){
        Candidate c = new Candidate();
        c.setId(1);
        c.setFullName("marko markovic");
        c.setBirthDate(LocalDate.parse("2000-01-01"));
        c.setContactNumber("060123123");
        c.setEmail("test@test.com");
        candidateRepo.save(c);
        Candidate cTest = candidateRepo.findById(1L).get();
        assertNotNull(cTest);
    }

    @Test
    @Order(2)
    void findAlLTest(){

        List<Candidate> candidateList = candidateRepo.findAll();
        assertNotEquals(0, candidateList.size());
    }

    @Test
    @Order(3)
    void findByIdTest(){
        Candidate c = candidateRepo.findById(1L).get();
        assertEquals("test@test.com",c.getEmail());
    }

    @Test
    @Order(4)
    void updateCandidateTest(){
        Candidate c = candidateRepo.findById(1L).get();
        c.setContactNumber("000000");
        candidateRepo.save(c);
        assertNotEquals("060123123", candidateRepo.findById(1L).get().getContactNumber());
    }

    @Test
    @Order(5)
    void findByFullName() {
        List<Candidate> candidateList = candidateRepo.findByFullName("marko markovic");
        assertFalse(candidateList.isEmpty());
    }

    @Test
    @Order(6)
    void deleteTest(){
        candidateRepo.deleteById(1L);
        assertFalse(candidateRepo.existsById(1L));
    }


}