package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.service.AmmunitionService;
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

    @GetMapping("weight-by-caliber")
    @ResponseBody
    public List<AmmunitionWeightDto> getWeightByCaliber(@RequestParam(name = "id")int id){

        return this.ammunitionService.findAmmunitionWeightByCaliberId(id);
    }

    @PostMapping("save/ammunition")
    @ResponseBody
    public AmmunitionDto newAmmunition(@Valid @RequestBody NewAmmunitionDto newAmmunitionDto) {

        return this.ammunitionService.save(newAmmunitionDto);
    }

    @PostMapping("save/factory")
    @ResponseBody
    public AmmunitionFactoryDto newFactory(@Valid @RequestBody NewAmmunitionFactoryDto newAmmunitionFactory){

        return this.ammunitionService.saveNewFactory(newAmmunitionFactory);
    }
    @GetMapping("all")
    @ResponseBody
    public List<AmmunitionDto> getAllAmmunition() {

        return this.ammunitionService.getAll();
    }

    @GetMapping("data-collection")
    @ResponseBody
    public AmmunitionDataCollection getDataCollection() {

        return this.ammunitionService.getDataCollection();
    }

}
