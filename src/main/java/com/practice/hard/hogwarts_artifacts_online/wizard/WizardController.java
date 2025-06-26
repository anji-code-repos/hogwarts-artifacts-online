package com.practice.hard.hogwarts_artifacts_online.wizard;

import com.practice.hard.hogwarts_artifacts_online.system.Result;
import com.practice.hard.hogwarts_artifacts_online.system.StatusCode;
import com.practice.hard.hogwarts_artifacts_online.wizard.converter.WizardDtoToWizardConverter;
import com.practice.hard.hogwarts_artifacts_online.wizard.converter.WizardToWizardDtoConverter;
import com.practice.hard.hogwarts_artifacts_online.wizard.dto.WizardDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/wizards")
public class WizardController {

    private final WizardService wizardService;

    private final WizardToWizardDtoConverter wizardToWizardDtoConverter;

    private final WizardDtoToWizardConverter wizardDtoToWizardConverter;

    public WizardController(WizardService wizardService,
                            WizardToWizardDtoConverter wizardToWizardDtoConverter,
                            WizardDtoToWizardConverter wizardDtoToWizardConverter) {
        this.wizardService = wizardService;
        this.wizardToWizardDtoConverter = wizardToWizardDtoConverter;
        this.wizardDtoToWizardConverter = wizardDtoToWizardConverter;
    }

    @GetMapping("/{wizardId}")
    public Result findArtifactById(@PathVariable Integer wizardId){
        WizardDto wizardDto= wizardToWizardDtoConverter.convert(this.wizardService.findWizardById(wizardId));
        return new Result(true, StatusCode.SUCCESS, "Find One Success", wizardDto);
    }

    @GetMapping
    public Result findAllArtifacts(){
        List<Wizard> wizardList= this.wizardService.getAllWizards();
        List<WizardDto> wizardDtoList= wizardList.stream().map(this.wizardToWizardDtoConverter::convert).toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", wizardDtoList);
    }

    @PostMapping
    public Result addAWizard(@Valid @RequestBody WizardDto wizardDto){
        Wizard wizard= this.wizardDtoToWizardConverter.convert(wizardDto);
        Wizard savedWizard= this.wizardService.saveWizard(wizard);
        WizardDto wizardDTO= this.wizardToWizardDtoConverter.convert(savedWizard);
        return new Result(true, StatusCode.SUCCESS, "Add Success", wizardDTO);
    }

    @PutMapping("/{wizardId}")
    public Result updateAWizard(@PathVariable Integer wizardId, @Valid @RequestBody WizardDto wizardDto){
        Wizard wizard= this.wizardService.updateWizard(wizardId, this.wizardDtoToWizardConverter.convert(wizardDto));
        WizardDto wizardDTO= this.wizardToWizardDtoConverter.convert(wizard);
        return new Result(true, StatusCode.SUCCESS, "Update Success", wizardDTO);
    }

    @DeleteMapping("/{wizardId}")
    public Result deleteAWizard(@PathVariable Integer wizardId){
        this.wizardService.deleteWizard(wizardId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success", null);
    }

}
