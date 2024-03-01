package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.FactoryType;
import fr.nicolas.godin.shoot_training_api.api.service.*;
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
    private FactoryService factoryService;
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


    @GetMapping("all/ammunition")
    @ResponseBody
    public List<AmmunitionDto> getAllAmmunition() {

        return this.ammunitionService.getAll();
    }

    @GetMapping("all/factory")
    @ResponseBody
    public List<FactoryDto> getAllFactories() {
        return this.factoryService.getAllByType(FactoryType.AMMUNITION);
    }

    @GetMapping("data-collection")
    @ResponseBody
    public AmmunitionDataCollection getDataCollection() {

        return new AmmunitionDataCollection(
                this.caliberService.getAllActive(),
               this.factoryService.getAllByType(FactoryType.AMMUNITION)
        );
    }

}
