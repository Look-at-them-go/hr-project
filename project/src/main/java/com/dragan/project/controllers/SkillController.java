package com.dragan.project.controllers;

import com.dragan.project.models.Skill;
import com.dragan.project.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/skills")
    public List<Skill> getSkills(){
        return skillService.findAllSkills();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/add-skill")
    public Skill addSkill(@RequestBody Skill skill){
        return skillService.saveSkill(skill);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/delete-skill/{id}")
    public String deleteSkill(@PathVariable long id){
        skillService.removeSkill(id);
        return "Deleted skill with id:" + id;
    }
}
