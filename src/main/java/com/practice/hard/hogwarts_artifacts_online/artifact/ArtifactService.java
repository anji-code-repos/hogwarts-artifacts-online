package com.practice.hard.hogwarts_artifacts_online.artifact;

import com.practice.hard.hogwarts_artifacts_online.artifact.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    private final IdWorker idWorker;

    public ArtifactService(ArtifactRepository artifactRepository, IdWorker idWorker) {
        this.artifactRepository = artifactRepository;
        this.idWorker = idWorker;
    }

    public Artifact findById(String artifactId){
        return this.artifactRepository.findById(artifactId).orElseThrow(
                ()->new ArtifactNotFoundException(artifactId));
    }

    public List<Artifact> findAllArtifacts(){
        return artifactRepository.findAll();
    }

    public Artifact save(Artifact artifact){
        artifact.setId(idWorker.nextId()+"");
        return this.artifactRepository.save(artifact);
    }

    public Artifact update(String artifactId, Artifact artifact)
    {
        return this.artifactRepository.findById(artifactId)
                .map(oldArtifact-> {
                    oldArtifact.setName(artifact.getName());
                    oldArtifact.setDescription(artifact.getDescription());
                    oldArtifact.setImageUrl(artifact.getImageUrl());
                    return this.artifactRepository.save(oldArtifact);
                })
                 .orElseThrow(()->new ArtifactNotFoundException(artifactId));
    }

    public void deleteArtifact(String artifactId){

        this.artifactRepository.findById(artifactId)
                .orElseThrow(()->new ArtifactNotFoundException(artifactId));

        this.artifactRepository.deleteById(artifactId);
    }
}
