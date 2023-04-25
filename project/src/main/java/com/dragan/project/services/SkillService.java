package com.dragan.project.services;

import com.dragan.project.models.Skill;
import com.dragan.project.repo.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepo skillRepo;

    public List<Skill> findAllSkills(){
        return skillRepo.findAll();
    }

    public Skill saveSkill(Skill skill){
        return skillRepo.save(skill);
    }

    public void removeSkill(long id){
        Skill deleteSkill = skillRepo.findById(id).get();
        skillRepo.delete(deleteSkill);
    }

}
