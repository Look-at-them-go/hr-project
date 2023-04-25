package com.dragan.project.services;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SkillServiceTest {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillRepo skillRepo;

    @Test
    @Order(1)
    void createSkillTest() {
        Skill s = new Skill();
        s.setId(1L);
        s.setName("java");

        skillService.saveSkill(s);
        Skill sTest = skillRepo.findById(1L).get();
        assertNotNull(sTest);
    }

    @Test
    @Order(2)
    void findAllSkillsTest() {
        List<Skill> skillList = skillService.findAllSkills();
        assertNotEquals(0, skillList.size());
    }



    @Test
    @Order(3)
    void removeSkillTest() {
        skillService.removeSkill(1L);
        assertFalse(skillRepo.existsById(1L));
    }
}