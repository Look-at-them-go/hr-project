package com.dragan.project.repo;

import com.dragan.project.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<Candidate, Long> {
}
