package com.practice.hard.hogwarts_artifacts_online.wizard;

import com.practice.hard.hogwarts_artifacts_online.artifact.utils.IdWorker;
import com.practice.hard.hogwarts_artifacts_online.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizardService {

    private final WizardRepository wizardRepository;
    private final IdWorker idWorker;

    public WizardService(WizardRepository wizardRepository, IdWorker idWorker) {
        this.idWorker=idWorker;
        this.wizardRepository = wizardRepository;
    }

    public Wizard findWizardById(Integer wizardId){
        return this.wizardRepository.findById(wizardId)
                .orElseThrow(()-> new ObjectNotFoundException("wizard", wizardId));
    }

    public List<Wizard> getAllWizards(){
        return this.wizardRepository.findAll();
    }

    public Wizard saveWizard(Wizard wizard){
        return this.wizardRepository.save(wizard);
    }

    public Wizard updateWizard(Integer wizardId, Wizard wizard){

        return this.wizardRepository.findById(wizardId)
                .map(oldwizard -> {
                    oldwizard.setName(wizard.getName());
                    return this.wizardRepository.save(oldwizard);
                }).orElseThrow(()-> new ObjectNotFoundException("wizard", wizardId));
    }


    public void deleteWizard(Integer wizardId){

        Wizard wizardToBeDeleted= this.wizardRepository.findById(wizardId)
                .orElseThrow(()-> new ObjectNotFoundException("wizard", wizardId));
        wizardToBeDeleted.removeAllArtifacts();
        this.wizardRepository.deleteById(wizardId);

    }
}

