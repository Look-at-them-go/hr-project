package com.dragan.project.controllers;

import com.dragan.project.models.Candidate;
import com.dragan.project.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/")
    public String getPage(){
        return "testing";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/candidates")
    public List<Candidate> getCandidates(){
        return candidateService.findAllCandidates();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/add-candidate")
    public Candidate addCandidate(@RequestBody Candidate candidate){
        return candidateService.saveCandidate(candidate);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/update-candidate/{id}")
    public String updateCandidate(@PathVariable long id, @RequestBody Candidate candidate){
        candidateService.updateCandidateData(id,candidate);
        return "updated";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/{candidateId}/assign-skill/{skillId}")
    public Candidate assignSkillToCandidate(@PathVariable long candidateId, @PathVariable long skillId){
        return candidateService.updateCandidateSkill(candidateId,skillId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/{candidateId}/remove-skill/{skillId}")
    public Candidate removeSkillFromCandidate(@PathVariable long candidateId, @PathVariable long skillId){
        return candidateService.removeSkill(candidateId,skillId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/search-by-name/{name}")
    public List<Candidate> getCandidatesByName(@PathVariable String name){
        return candidateService.findCandidateByName(name);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/search-by-skill/{skillIds}")
    public List<Candidate> getCandidatesBySkill(@PathVariable List<Long> skillIds){
        return candidateService.findCandidateBySkills(skillIds);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/delete-candidate/{id}")
    public String deleteCandidate(@PathVariable long id){
        candidateService.removeCandidate(id);
        return "Deleted user with id:" + id;
    }
}
