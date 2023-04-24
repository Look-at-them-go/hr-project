package com.dragan.project.controllers;

import com.dragan.project.models.Candidate;
import com.dragan.project.models.Skill;
import com.dragan.project.repo.CandidateRepo;
import com.dragan.project.repo.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CandidateController {

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private SkillRepo skillRepo;

    @GetMapping(value = "/")
    public String getPage(){
        return "testing";
    }

    @GetMapping(value = "/candidates")
    public List<Candidate> getCandidates(){
        return candidateRepo.findAll();
    }

    @PostMapping(value = "/add-candidate")
    public Candidate addCandidate(@RequestBody Candidate candidate){
        return candidateRepo.save(candidate);
    }

    @PutMapping(value = "/update-candidate/{id}")
    public String updateCandidate(@PathVariable long id, @RequestBody Candidate candidate){
        Candidate updatedCandidate = candidateRepo.findById(id).get();
        updatedCandidate.setFullName(candidate.getFullName());
        updatedCandidate.setBirthDate(candidate.getBirthDate());
        updatedCandidate.setContactNumber(candidate.getContactNumber());
        updatedCandidate.setEmail(candidate.getEmail());
        candidateRepo.save(updatedCandidate);
        return "updated";
    }

    @PutMapping(value = "/{candidateId}/assign-skill/{skillId}")
    public Candidate assignSkillToCandidate(@PathVariable long candidateId, @PathVariable long skillId){
        Set<Skill> skillSet = null;

        Candidate candidate = candidateRepo.findById(candidateId).get();
        Skill skill = skillRepo.findById(skillId).get();

        skillSet = candidate.getCandidateSkills();
        skillSet.add(skill);
        candidate.setCandidateSkills(skillSet);
        return candidateRepo.save(candidate);
    }

    @PutMapping(value = "/{candidateId}/remove-skill/{skillId}")
    public Candidate removeSkillFromCandidate(@PathVariable long candidateId, @PathVariable long skillId){
        Set<Skill> skillSet = null;

        Candidate candidate = candidateRepo.findById(candidateId).get();
        Skill skill = skillRepo.findById(skillId).get();

        skillSet = candidate.getCandidateSkills();
        skillSet.remove(skill);
        candidate.setCandidateSkills(skillSet);
        return candidateRepo.save(candidate);
    }

    @DeleteMapping(value = "/delete-candidate/{id}")
    public String deleteCandidate(@PathVariable long id){
        Candidate deleteCandidate = candidateRepo.findById(id).get();
        candidateRepo.delete(deleteCandidate);
        return "Deleted user with id:" + id;
    }
}
