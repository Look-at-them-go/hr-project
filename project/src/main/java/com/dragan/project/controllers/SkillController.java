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

    @GetMapping(value = "/skills")
    public List<Skill> getSkills(){
        return skillService.findAllSkills();
    }

    @PostMapping(value = "/add-skill")
    public Skill addSkill(@RequestBody Skill skill){
        return skillService.saveSkill(skill);
    }

    @DeleteMapping(value = "/delete-skill/{id}")
    public String deleteSkill(@PathVariable long id){
        skillService.removeSkill(id);
        return "Deleted skill with id:" + id;
    }
}
