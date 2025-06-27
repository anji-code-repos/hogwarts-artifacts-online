package com.practice.hard.hogwarts_artifacts_online.wizard;

import com.practice.hard.hogwarts_artifacts_online.artifact.Artifact;
import com.practice.hard.hogwarts_artifacts_online.artifact.ArtifactRepository;
import com.practice.hard.hogwarts_artifacts_online.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WizardServiceTest {

    @Mock
    WizardRepository wizardRepository;

    @Mock
    ArtifactRepository artifactRepository;

    @InjectMocks
    WizardService wizardService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAssignAnArtifactToAWizardSuccess(){

        //Given: Define Inputs & data: Create Mock Data
        Artifact artifact= new Artifact();
        artifact.setId("1250808601744904192");
        artifact.setName("Invisibility Cloak");
        artifact.setDescription("An invisibility cloak is used to make the wearer invisible.");
        artifact.setImageUrl("ImageUrl");

        Wizard w2= new Wizard();
        w2.setId(2);
        w2.setName("Harry Potter");
        w2.addArtifact(artifact);

        Wizard w3= new Wizard();
        w3.setId(3);
        w3.setName("Neville Longbottom");

        given(this.artifactRepository.findById("1250808601744904192")).willReturn(Optional.of(artifact));
        given(this.wizardRepository.findById(3)).willReturn(Optional.of(w3));

        //When
        this.wizardService.assignAnArtifactToAWizard(3, "1250808601744904192");

        //Then
        assertThat(artifact.getOwner().getId()).isEqualTo(3);
        assertThat(w3.getArtifacts()).contains(artifact);
    }


    @Test
    void testAssignAnArtifactToWizardErrorWithNonExistentArtifactId() {

        Wizard w3 = new Wizard();
        w3.setId(3);
        w3.setName("Neville Longbottom");

        given(this.artifactRepository.findById("1250808601744904192")).willReturn(Optional.empty());

        Throwable thrown = assertThrows(ObjectNotFoundException.class,
                () -> this.wizardService.assignAnArtifactToAWizard(3, "1250808601744904192"));

        assertThat(thrown).
                isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find artifact with Id 1250808601744904192");

    }

    @Test
    void testAssignAnArtifactToWizardErrorWithNonExistentWizardId(){

        Artifact artifact= new Artifact();
        artifact.setId("1250808601744904192");
        artifact.setName("Invisibility Cloak");
        artifact.setDescription("An invisibility cloak is used to make the wearer invisible.");
        artifact.setImageUrl("ImageUrl");

        Wizard w2= new Wizard();
        w2.setId(2);
        w2.setName("Harry Potter");
        w2.addArtifact(artifact);

        given(this.artifactRepository.findById("1250808601744904192")).willReturn(Optional.of(artifact));

        given(this.wizardRepository.findById(3)).willReturn(Optional.empty());

        Throwable thrown = assertThrows(ObjectNotFoundException.class,
                ()-> this.wizardService.assignAnArtifactToAWizard(3, "1250808601744904192"));

        assertThat(thrown).
                isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find wizard with Id 3");
    }

}