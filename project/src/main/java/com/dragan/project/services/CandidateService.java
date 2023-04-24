package com.dragan.project.services;

import com.dragan.project.models.Candidate;
import com.dragan.project.models.Skill;
import com.dragan.project.repo.CandidateRepo;
import com.dragan.project.repo.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private SkillRepo skillRepo;

    public List<Candidate> findAllCandidates(){
        return candidateRepo.findAll();
    }

    public Candidate saveCandidate(Candidate candidate){
        return candidateRepo.save(candidate);
    }

    public void updateCandidateData(long id, Candidate candidate){
        Candidate updatedCandidate = candidateRepo.findById(id).get();
        updatedCandidate.setFullName(candidate.getFullName());
        updatedCandidate.setBirthDate(candidate.getBirthDate());
        updatedCandidate.setContactNumber(candidate.getContactNumber());
        updatedCandidate.setEmail(candidate.getEmail());
        candidateRepo.save(updatedCandidate);
    }

    public Candidate updateCandidateSkill(long candidateId, long skillId ){
        Set<Skill> skillSet = null;

        Candidate candidate = candidateRepo.findById(candidateId).get();
        Skill skill = skillRepo.findById(skillId).get();

        skillSet = candidate.getCandidateSkills();
        skillSet.add(skill);
        candidate.setCandidateSkills(skillSet);
        return candidateRepo.save(candidate);
    }

    public Candidate removeSkill(long candidateId, long skillId){
        Set<Skill> skillSet = null;

        Candidate candidate = candidateRepo.findById(candidateId).get();
        Skill skill = skillRepo.findById(skillId).get();

        skillSet = candidate.getCandidateSkills();
        skillSet.remove(skill);
        candidate.setCandidateSkills(skillSet);
        return candidateRepo.save(candidate);
    }

    public List<Candidate> findCandidateByName(String name){
        return candidateRepo.findByFullName(name);
    }

    public void removeCandidate(long id){
        Candidate deleteCandidate = candidateRepo.findById(id).get();
        candidateRepo.delete(deleteCandidate);
    }
}
