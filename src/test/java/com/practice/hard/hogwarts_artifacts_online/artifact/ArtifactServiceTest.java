package com.practice.hard.hogwarts_artifacts_online.artifact;

import com.practice.hard.hogwarts_artifacts_online.artifact.utils.IdWorker;
import com.practice.hard.hogwarts_artifacts_online.system.exception.ObjectNotFoundException;
import com.practice.hard.hogwarts_artifacts_online.wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {

    @Mock
    ArtifactRepository artifactRepository;

    @Mock
    IdWorker idWorker;

    @InjectMocks
    ArtifactService artifactService;

    List<Artifact> artifacts;

    @BeforeEach
    void setUp() {

        this.artifacts=new ArrayList<>();

        Artifact a1 = new Artifact();
        a1.setId("1250808601744904191");
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.");
        a1.setImageUrl("ImageUrl");
        this.artifacts.add(a1);

        Artifact a2 = new Artifact();
        a2.setId("1250808601744904192");
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible.");
        a2.setImageUrl("ImageUrl");
        this.artifacts.add(a2);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {

        // Given
        Artifact artifact= new Artifact();

        artifact.setId("1250808601744904192");
        artifact.setName("Invisibility Cloak");
        artifact.setDescription("An invisibility cloak is used to make the wearer invisible.");
        artifact.setImageUrl("ImageUrl");

        Wizard wizard=new Wizard();
        wizard.setId(2);
        wizard.setName("Harry Potter");

        artifact.setOwner(wizard);

        given(artifactRepository.findById("1250808601744904192")).willReturn(Optional.of(artifact));

        // When

        Artifact returnedArtifact= artifactService.findById("1250808601744904192");

        // Then

        assertThat(returnedArtifact.getId()).isEqualTo(artifact.getId());
        assertThat(returnedArtifact.getName()).isEqualTo(artifact.getName());
        assertThat(returnedArtifact.getDescription()).isEqualTo(artifact.getDescription());
        assertThat(returnedArtifact.getImageUrl()).isEqualTo(artifact.getImageUrl());

        verify(artifactRepository, times(1)).findById("1250808601744904192");

    }


    @Test
    void testFindByIdNotFound() {


        //Given
        given(artifactRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        //When

        Throwable thrown = catchThrowable(() -> {

            Artifact returnedArtifact = artifactService.findById("1250808601744904192");

        });

        //Then

        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find artifact with Id 1250808601744904192");

        verify(artifactRepository, times(1)).findById("1250808601744904192");
    }




    @Test
    void testFindAllArtifactsSuccess() {

        given(artifactRepository.findAll()).willReturn(this.artifacts);

        List<Artifact> artifactList = artifactService.findAllArtifacts();

        assertThat(artifactList.size()).isEqualTo(this.artifacts.size());

        verify(artifactRepository, times(1)).findAll();

    }



    @Test
    void testSaveArtifactSuccess(){

        Artifact newArtifact= new Artifact();
        newArtifact.setName("new artifact");
        newArtifact.setDescription("artifact description");
        newArtifact.setImageUrl("image url");

        given(idWorker.nextId()).willReturn(123L);
        given(artifactRepository.save(newArtifact)).willReturn(newArtifact);

        Artifact savedArtifact= artifactService.save(newArtifact);

        assertThat(savedArtifact.getId()).isEqualTo("123");
        assertThat(savedArtifact.getName()).isEqualTo(newArtifact.getName());
        assertThat(savedArtifact.getDescription()).isEqualTo(newArtifact.getDescription());
        assertThat(savedArtifact.getImageUrl()).isEqualTo(newArtifact.getImageUrl());
        verify(artifactRepository,times(1)).save(newArtifact);

    }

    @Test
    void testUpdateAnArtifactSuccess(){

        //Given
        Artifact oldArtifact = new Artifact();
        oldArtifact.setId("1250808601744904192");
        oldArtifact.setName("Invisibility Cloak");
        oldArtifact.setDescription("An invisibility cloak is used to make the wearer invisible.");
        oldArtifact.setImageUrl("ImageUrl");


        Artifact update = new Artifact();
        update.setId("1250808601744904192");
        update.setName("Invisibility Cloak");
        update.setDescription("New description for artifact");
        update.setImageUrl("ImageUrl");

        given(artifactRepository.findById("1250808601744904192")).willReturn(Optional.of(oldArtifact));
        given(artifactRepository.save(oldArtifact)).willReturn(oldArtifact);

        //When

        Artifact updatedArtifact= artifactService.update("1250808601744904192", update);

        //Then

        assertThat(updatedArtifact.getName()).isEqualTo(update.getName());
        assertThat(updatedArtifact.getDescription()).isEqualTo(update.getDescription());
        verify(artifactRepository, times(1)).findById("1250808601744904192");
        verify(artifactRepository, times(1)).save(oldArtifact);
    }


    @Test
    void testUpdateArtifactNotFound(){

        Artifact update = new Artifact();
        update.setId("1250808601744904192");
        update.setName("Invisibility Cloak");
        update.setDescription("New description for artifact");
        update.setImageUrl("ImageUrl");

        given(this.artifactRepository.findById("1250808601744904192")).willReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFoundException.class,
                ()-> artifactService.update("1250808601744904192", update));

        verify(artifactRepository, times(1)).findById("1250808601744904192");
    }


    @Test
    void testDeleteAnArtifactSuccess(){

        //Given
        Artifact artifactToBeDeleted = new Artifact();
        artifactToBeDeleted.setId("1250808601744904192");
        artifactToBeDeleted.setName("Invisibility Cloak");
        artifactToBeDeleted.setDescription("An invisibility cloak is used to make the wearer invisible.");
        artifactToBeDeleted.setImageUrl("ImageUrl");

        given(this.artifactRepository.findById("1250808601744904192")).willReturn(Optional.of(artifactToBeDeleted));
        doNothing().when(artifactRepository).deleteById("1250808601744904192");

        //When
        artifactService.deleteArtifact("1250808601744904192");

        //Then
        verify(artifactRepository, times(1)).deleteById("1250808601744904192");
    }



    @Test
    void testDeleteArtifactNotFound(){

        //Given
        given(this.artifactRepository.findById("1250808601744904192")).willReturn(Optional.empty());

        //When
        Assertions.assertThrows(ObjectNotFoundException.class,
                ()-> artifactService.deleteArtifact("1250808601744904192"));

        //Then
        verify(artifactRepository, times(1)).findById("1250808601744904192");
    }


}