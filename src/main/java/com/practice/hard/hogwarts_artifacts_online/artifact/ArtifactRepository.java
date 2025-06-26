package com.practice.hard.hogwarts_artifacts_online.artifact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, String>{

    // <T, ID>

    //T refers to the Domain Type the repository manages

    //ID refers to the type of ID of the entity the repository manages --> String
}
