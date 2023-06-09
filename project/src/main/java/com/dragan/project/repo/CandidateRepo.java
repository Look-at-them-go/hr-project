package com.dragan.project.repo;

import com.dragan.project.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    public List<Candidate> findByFullName(String fullName);
}
