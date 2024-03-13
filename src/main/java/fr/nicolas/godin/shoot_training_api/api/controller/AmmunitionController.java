package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.service.*;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
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
    private AmmunitionWeightService ammunitionWeightService;


    @GetMapping("weight-by-caliber")
    @ResponseBody
    public List<AmmunitionWeightDto> getWeightByCaliber(@RequestParam(name = "id")int id){

        return this.ammunitionWeightService.findAmmunitionWeightByCaliberId(id);
    }

    @GetMapping("all/weight")
    @ResponseBody
    public List<AmmunitionWeightDto> getAllWeight(){
        return this.ammunitionWeightService.getAll();
    }

    @PostMapping("save/ammunition")
    @ResponseBody
    public AmmunitionDto newAmmunition(@Valid @RequestBody AmmunitionCreateDto ammunitionCreateDto) {

        return this.ammunitionService.create(ammunitionCreateDto);
    }

    @PostMapping("save/weight")
    @ResponseBody
    public AmmunitionWeightDto newWeight(@Valid @RequestBody AmmunitionWeightCreateDto ammunitionWeightCreateDto){
        return this.ammunitionWeightService.create(ammunitionWeightCreateDto);
    }

    @GetMapping("all/ammunition")
    @ResponseBody
    public List<AmmunitionDto> getAllAmmunition() {

        return this.ammunitionService.getAll();
    }



}
