package com.practice.hard.hogwarts_artifacts_online.wizard.dto;

import jakarta.validation.constraints.NotEmpty;

public record WizardDto(Integer id,

                        @NotEmpty(message = "name is required")
                        String name,

                        Integer getNumberOfArtifacts ) {
}
