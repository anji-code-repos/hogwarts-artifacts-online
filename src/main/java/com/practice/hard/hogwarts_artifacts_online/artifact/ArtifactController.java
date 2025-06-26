package com.practice.hard.hogwarts_artifacts_online.artifact;

import com.practice.hard.hogwarts_artifacts_online.artifact.converter.ArtifactDtoToArtifactConverter;
import com.practice.hard.hogwarts_artifacts_online.artifact.converter.ArtifactToArtifactDtoConverter;
import com.practice.hard.hogwarts_artifacts_online.artifact.dto.ArtifactDto;
import com.practice.hard.hogwarts_artifacts_online.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import com.practice.hard.hogwarts_artifacts_online.system.Result;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/artifacts")
public class ArtifactController {

    private final ArtifactService artifactService;

    private final ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter;

    private final ArtifactDtoToArtifactConverter artifactDtoToArtifactConverter;


    public ArtifactController(ArtifactService artifactService,
                              ArtifactToArtifactDtoConverter artifactToArtifactDtoConverter,
                              ArtifactDtoToArtifactConverter artifactDtoToArtifactConverter) {
        this.artifactService = artifactService;
        this.artifactToArtifactDtoConverter = artifactToArtifactDtoConverter;
        this.artifactDtoToArtifactConverter = artifactDtoToArtifactConverter;
    }

    @GetMapping("/{artifactId}")
    public Result findArtifactById(@PathVariable String artifactId){
        Artifact foundArtifact= this.artifactService.findById(artifactId);
        ArtifactDto artifactDto=this.artifactToArtifactDtoConverter.convert(foundArtifact);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", artifactDto);
    }


    @GetMapping
    public Result findAllArtifacts(){
        List<Artifact> artifactList= this.artifactService.findAllArtifacts();
        List<ArtifactDto> artifactDtos= artifactList.stream().map(this.artifactToArtifactDtoConverter::convert).collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", artifactDtos);
    }


    @PostMapping
    public Result addAnArtifact(@Valid @RequestBody ArtifactDto artifactDto){
        Artifact newArtifact= this.artifactDtoToArtifactConverter.convert(artifactDto);
        Artifact savedArtifact= this.artifactService.save(newArtifact);
        ArtifactDto savedArtifactDto= this.artifactToArtifactDtoConverter.convert(savedArtifact);
        return new Result(true, StatusCode.SUCCESS, "Add Artifact Success", savedArtifactDto);
    }

    @PutMapping("/{artifactId}")
    public Result updateArtifact(@PathVariable String artifactId, @Valid @RequestBody ArtifactDto artifactDto){
        Artifact artifact= this.artifactDtoToArtifactConverter.convert(artifactDto);
        Artifact updatedArtifact= this.artifactService.update(artifactId, artifact);
        ArtifactDto updated= this.artifactToArtifactDtoConverter.convert(updatedArtifact);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updated);
    }

    @DeleteMapping("/{artifactId}")
    public Result deleteAnArtifact(@PathVariable String artifactId){
        this.artifactService.deleteArtifact(artifactId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success", null);
    }

}
