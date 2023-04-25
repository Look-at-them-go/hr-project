package com.dragan.project.repo;

import com.dragan.project.models.Candidate;
import com.dragan.project.models.Skill;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SkillRepoTest {

    @Autowired
    private SkillRepo skillRepo;

    @Test
    @Order(1)
    void createSkillTest(){
        Skill s = new Skill();
        s.setId(1);
        s.setName("java");
        skillRepo.save(s);
        Skill sTest = skillRepo.findById(1L).get();
        assertNotNull(sTest);
    }

    @Test
    @Order(2)
    void findAllTest(){
        List<Skill> skillList = skillRepo.findAll();
        assertNotEquals(0, skillList.size());
    }

    @Test
    @Order(3)
    void findByIdTest(){
        Skill s = skillRepo.findById(1L).get();
        assertEquals("java",s.getName());
    }

    @Test
    @Order(4)
    void deleteSkillTest(){
        skillRepo.deleteById(1L);
        assertFalse(skillRepo.existsById(1L));
    }

}