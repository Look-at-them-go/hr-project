package com.dragan.project.controllers;

import com.dragan.project.models.Candidate;
import com.dragan.project.models.Skill;
import com.dragan.project.repo.CandidateRepo;
import com.dragan.project.repo.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillRepo skillRepo;

    @GetMapping(value = "/skills")
    public List<Skill> getSkills(){
        return skillRepo.findAll();
    }

    @PostMapping(value = "/add-skill")
    public Skill addSkill(@RequestBody Skill skill){
        return skillRepo.save(skill);
    }

    @DeleteMapping(value = "/delete-skill/{id}")
    public String deleteSkill(@PathVariable long id){
        Skill deleteSkill = skillRepo.findById(id).get();
        skillRepo.delete(deleteSkill);
        return "Deleted skill with id:" + id;
    }
}
