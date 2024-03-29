package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightDto;
import fr.nicolas.godin.shoot_training_api.api.service.AmmunitionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Ammunition",description = "Ammunition Controller")
@RequestMapping(value = "/api/ammunition",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AmmunitionController {

    private AmmunitionService ammunitionService;

    @GetMapping("weight-by-caliber")
    public List<AmmunitionWeightDto> getWeightByCaliber(@RequestParam(name = "id")int id){

        return this.ammunitionService.findAmmunitionWeightByCaliberId(id);
    }
}
