package com.practice.hard.hogwarts_artifacts_online.system;

import com.practice.hard.hogwarts_artifacts_online.artifact.Artifact;
import com.practice.hard.hogwarts_artifacts_online.artifact.ArtifactRepository;
import com.practice.hard.hogwarts_artifacts_online.hogwartsUser.HogwartsUser;
import com.practice.hard.hogwarts_artifacts_online.hogwartsUser.UserRepository;
import com.practice.hard.hogwarts_artifacts_online.wizard.Wizard;
import com.practice.hard.hogwarts_artifacts_online.wizard.WizardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final ArtifactRepository artifactRepository;
    private final WizardRepository wizardRepository;
    private final UserRepository userRepository;

    public DBDataInitializer(ArtifactRepository artifactRepository, WizardRepository wizardRepository, UserRepository userRepository){
        this.artifactRepository=artifactRepository;
        this.wizardRepository=wizardRepository;
        this.userRepository= userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Artifact a1 = new Artifact();
        a1.setId("1250808601744904191");
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.");
        a1.setImageUrl("ImageUrl");

        Artifact a2 = new Artifact();
        a2.setId("1250808601744904192");
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible.");
        a2.setImageUrl("ImageUrl");

        Artifact a3 = new Artifact();
        a3.setId("1250808601744904193");
        a3.setName("Elder Wand");
        a3.setDescription("The Elder Wand, known throughout history as the Deathstick or the Wand of Destiny, is an extremely powerful wand made of elder wood with a core of Thestral tail hair.");
        a3.setImageUrl("ImageUrl");

        Artifact a4 = new Artifact();
        a4.setId("1250808601744904194");
        a4.setName("The Marauder's Map");
        a4.setDescription("A magical map of Hogwarts created by Remus Lupin, Peter Pettigrew, Sirius Black, and James Potter while they were students at Hogwarts.");
        a4.setImageUrl("ImageUrl");

        Artifact a5 = new Artifact();
        a5.setId("1250808601744904195");
        a5.setName("The Sword Of Gryffindor");
        a5.setDescription("A goblin-made sword adorned with large rubies on the pommel. It was once owned by Godric Gryffindor, one of the medieval founders of Hogwarts.");
        a5.setImageUrl("ImageUrl");

        Artifact a6 = new Artifact();
        a6.setId("1250808601744904196");
        a6.setName("Resurrection Stone");
        a6.setDescription("The Resurrection Stone allows the holder to bring back deceased loved ones, in a semi-physical form, and communicate with them.");
        a6.setImageUrl("ImageUrl");

        Wizard w1=new Wizard();
        w1.setName("Albus Dumbledore");
        w1.addArtifact(a1);
        w1.addArtifact(a3);

        Wizard w2= new Wizard();
        w2.setName("Harry Potter");
        w2.addArtifact(a2);
        w2.addArtifact(a4);

        Wizard w3= new Wizard();
        w3.setName("Neville Longbottom");
        w3.addArtifact(a5);

        HogwartsUser hogwartsUser1= new HogwartsUser();
        hogwartsUser1.setName("Harvard");
        hogwartsUser1.setEnabled(true);
        hogwartsUser1.setRoles("Admin roles");
        hogwartsUser1.setPassword("Unity!");

        HogwartsUser hogwartsUser2= new HogwartsUser();
        hogwartsUser2.setName("Brown");
        hogwartsUser2.setEnabled(true);
        hogwartsUser2.setRoles("User roles");
        hogwartsUser2.setPassword("Integrity!");

        HogwartsUser hogwartsUser3= new HogwartsUser();
        hogwartsUser3.setName("Ben");
        hogwartsUser3.setEnabled(true);
        hogwartsUser3.setRoles("User roles");
        hogwartsUser3.setPassword("Togetherness!");

        this.wizardRepository.save(w1);
        this.wizardRepository.save(w2);
        this.wizardRepository.save(w3);

        this.artifactRepository.save(a6);

        this.userRepository.save(hogwartsUser1);
        this.userRepository.save(hogwartsUser2);
        this.userRepository.save(hogwartsUser3);
    }
}
