package com.practice.hard.hogwarts_artifacts_online.artifact.converter;

import com.practice.hard.hogwarts_artifacts_online.artifact.Artifact;
import com.practice.hard.hogwarts_artifacts_online.artifact.dto.ArtifactDto;
import com.practice.hard.hogwarts_artifacts_online.wizard.converter.WizardToWizardDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArtifactToArtifactDtoConverter implements Converter<Artifact, ArtifactDto> {


    private final WizardToWizardDtoConverter wizardToWizardDtoConverter;

    public ArtifactToArtifactDtoConverter(WizardToWizardDtoConverter wizardToWizardDtoConverter) {
        this.wizardToWizardDtoConverter = wizardToWizardDtoConverter;
    }

    @Override
    public ArtifactDto convert(Artifact source) {

        return new ArtifactDto(source.getId(),
                source.getName(),
                source.getDescription(),
                source.getImageUrl(),
                source.getOwner()!=null
                       ? this.wizardToWizardDtoConverter.convert(source.getOwner())
                       : null);
    }
}
