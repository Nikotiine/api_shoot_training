package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.service.AmmunitionFactoryService;
import fr.nicolas.godin.shoot_training_api.api.service.AmmunitionService;
import fr.nicolas.godin.shoot_training_api.api.service.AmmunitionWeightService;
import fr.nicolas.godin.shoot_training_api.api.service.CaliberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Ammunition",description = "Ammunition Controller")
@RequestMapping(value = "/api/ammunition",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AmmunitionController {

    private AmmunitionService ammunitionService;
    private AmmunitionFactoryService ammunitionFactoryService;
    private AmmunitionWeightService ammunitionWeightService;
    private CaliberService caliberService;

    @GetMapping("weight-by-caliber")
    @ResponseBody
    public List<AmmunitionWeightDto> getWeightByCaliber(@RequestParam(name = "id")int id){

        return this.ammunitionWeightService.findAmmunitionWeightByCaliberId(id);
    }

    @PostMapping("save/ammunition")
    @ResponseBody
    public AmmunitionDto newAmmunition(@Valid @RequestBody NewAmmunitionDto newAmmunitionDto) {

        return this.ammunitionService.create(newAmmunitionDto);
    }

    @PostMapping("save/factory")
    @ResponseBody
    public AmmunitionFactoryDto newFactory(@Valid @RequestBody NewAmmunitionFactoryDto newAmmunitionFactory){

        return this.ammunitionFactoryService.create(newAmmunitionFactory);
    }
    @GetMapping("all")
    @ResponseBody
    public List<AmmunitionDto> getAllAmmunition() {

        return this.ammunitionService.getAll();
    }

    @GetMapping("data-collection")
    @ResponseBody
    public AmmunitionDataCollection getDataCollection() {

        return new AmmunitionDataCollection(
                this.caliberService.getAllActive(),
                this.ammunitionFactoryService.getAllActive()
        );
    }

}
