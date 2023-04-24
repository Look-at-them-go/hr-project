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

    @GetMapping(value = "/")
    public String getPage(){
        return "testing";
    }

    @GetMapping(value = "/candidates")
    public List<Candidate> getCandidates(){
        return candidateService.findAllCandidates();
    }

    @PostMapping(value = "/add-candidate")
    public Candidate addCandidate(@RequestBody Candidate candidate){
        return candidateService.saveCandidate(candidate);
    }

    @PutMapping(value = "/update-candidate/{id}")
    public String updateCandidate(@PathVariable long id, @RequestBody Candidate candidate){
        candidateService.updateCandidateData(id,candidate);
        return "updated";
    }

    @PutMapping(value = "/{candidateId}/assign-skill/{skillId}")
    public Candidate assignSkillToCandidate(@PathVariable long candidateId, @PathVariable long skillId){
        return candidateService.updateCandidateSkill(candidateId,skillId);
    }

    @PutMapping(value = "/{candidateId}/remove-skill/{skillId}")
    public Candidate removeSkillFromCandidate(@PathVariable long candidateId, @PathVariable long skillId){
        return candidateService.removeSkill(candidateId,skillId);
    }

    @GetMapping(value = "/search-by-name/{name}")
    public List<Candidate> getCandidatesByName(@PathVariable String name){
        return candidateService.findCandidateByName(name);
    }


    @DeleteMapping(value = "/delete-candidate/{id}")
    public String deleteCandidate(@PathVariable long id){
        candidateService.removeCandidate(id);
        return "Deleted user with id:" + id;
    }
}
