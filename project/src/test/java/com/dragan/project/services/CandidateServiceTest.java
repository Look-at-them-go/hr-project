package com.dragan.project.services;

import com.dragan.project.models.Candidate;
import com.dragan.project.models.Skill;
import com.dragan.project.repo.CandidateRepo;
import com.dragan.project.repo.SkillRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.transaction.annotation.Propagation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CandidateServiceTest {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private SkillRepo skillRepo;

    @Test
    @Order(1)
    void saveCandidateTest() {
        Candidate a = new Candidate();
        a.setId(1L);
        a.setFullName("marko");
        a.setBirthDate(LocalDate.parse("2000-10-01"));
        a.setContactNumber("000000");
        a.setEmail("a@test.com");

        Candidate b = new Candidate();
        b.setId(2L);
        b.setFullName("dusan");
        b.setBirthDate(LocalDate.parse("2000-09-01"));
        b.setContactNumber("111111");
        b.setEmail("b@test.com");

        Candidate c = new Candidate();
        c.setId(3L);
        c.setFullName("petar");
        c.setBirthDate(LocalDate.parse("2000-08-01"));
        c.setContactNumber("222222");
        c.setEmail("c@test.com");

        candidateService.saveCandidate(a);
        candidateService.saveCandidate(b);
        candidateService.saveCandidate(c);

        Candidate aTest = candidateRepo.findById(1L).get();
        Candidate bTest = candidateRepo.findById(2L).get();
        Candidate cTest = candidateRepo.findById(3L).get();

        assertNotNull(aTest);
        assertNotNull(bTest);
        assertNotNull(cTest);
    }

    @Test
    @Order(2)
    void findAllCandidatesTest() {
        List<Candidate> candidateList = candidateService.findAllCandidates();
        assertNotEquals(0,candidateList.size());
    }


    @Test
    @Order(3)
    void updateCandidateDataTest() {
        Candidate aUpdated = new Candidate();
        aUpdated.setFullName("milan");
        aUpdated.setBirthDate(LocalDate.parse("2000-10-01"));
        aUpdated.setContactNumber("000000");
        aUpdated.setEmail("a@test.com");

        candidateService.updateCandidateData(1,aUpdated);
        assertEquals(candidateRepo.findById(1L).get().getFullName(), "milan");
    }

    @Test
    @Order(4)
    @Transactional
    void updateRemoveCandidateSkillTest() {
        Skill s1 = new Skill();
        s1.setId(1L);
        s1.setName("java");
        skillRepo.save(s1);

        Skill s2 = new Skill();
        s2.setId(2L);
        s2.setName("C");
        skillRepo.save(s2);

        Skill s3 = new Skill();
        s3.setId(3L);
        s3.setName("C++");
        skillRepo.save(s3);

        candidateService.updateCandidateSkill(1L,1L);
        candidateService.updateCandidateSkill(1L,2L);

        candidateService.updateCandidateSkill(2L,2L);

        candidateService.updateCandidateSkill(3L,1L);
        candidateService.updateCandidateSkill(3L,2L);
        candidateService.updateCandidateSkill(3L,3L);

        assertNotEquals(0, candidateRepo.findById(1L).get().getCandidateSkills().size());
        assertNotEquals(0, candidateRepo.findById(2L).get().getCandidateSkills().size());
        assertNotEquals(0, candidateRepo.findById(3L).get().getCandidateSkills().size());


        // remove test
        candidateService.removeSkill(1L,2L);

        assertEquals(1,candidateRepo.findById(1L).get().getCandidateSkills().size());
    }



    @Test
    @Order(5)
    void findCandidateByNameTest() {
        List<Candidate> candidateList = candidateService.findCandidateByName("milan");
        assertNotEquals(0,candidateList.size());
    }


    @Test
    @Order(6)
    void removeCandidateTest() {
        candidateService.removeCandidate(1L);
        candidateService.removeCandidate(2L);
        candidateService.removeCandidate(3L);
        assertFalse(candidateRepo.existsById(1L));
    }
}