package com.practice.hard.hogwarts_artifacts_online.wizard;

import com.practice.hard.hogwarts_artifacts_online.artifact.Artifact;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wizard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner")
    private List<Artifact> artifacts= new ArrayList<>();

    public Wizard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public void addArtifact(Artifact artifact){
        artifact.setOwner(this);
        this.artifacts.add(artifact);
    }

    public Integer getNoOfArtifacts() {
        return this.artifacts.size();
    }

    public void removeAllArtifacts() {
        this.getArtifacts().stream().forEach(artifact -> artifact.setOwner(null));
        this.artifacts=new ArrayList<>();
    }

    public void removeArtifact(Artifact artifactToBeAssigned) {
        artifactToBeAssigned.setOwner(null);
        this.artifacts.remove(artifactToBeAssigned);
    }
}
