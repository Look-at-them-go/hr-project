package com.dragan.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "candidateSkills")
    private Set<Candidate> candidateSet = new HashSet<>();



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Candidate> getCandidateSet() {
        return candidateSet;
    }

    public void setCandidateSet(Set<Candidate> candidateSet) {
        this.candidateSet = candidateSet;
    }
}
