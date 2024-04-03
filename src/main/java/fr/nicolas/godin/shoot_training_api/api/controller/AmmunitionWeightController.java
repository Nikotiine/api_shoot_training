package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightDto;
import fr.nicolas.godin.shoot_training_api.api.service.AmmunitionWeightService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Weight",description = "Ammunition Weight Controller")
@RequestMapping(value = "/api/weight",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AmmunitionWeightController {
    private AmmunitionWeightService ammunitionWeightService;

    @GetMapping("by-caliber")
    @ResponseBody
    public List<AmmunitionWeightDto> getWeightByCaliber(@RequestParam(name = "id")int id){

        return this.ammunitionWeightService.findAmmunitionWeightByCaliberId(id);
    }

    @PostMapping("save")
    @ResponseBody
    public AmmunitionWeightDto newWeight(@Valid @RequestBody AmmunitionWeightCreateDto ammunitionWeightCreateDto){
        return this.ammunitionWeightService.create(ammunitionWeightCreateDto);
    }
    @GetMapping("all")
    @ResponseBody
    public List<AmmunitionWeightDto> getAllWeight(){

        return this.ammunitionWeightService.getAll();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("delete")
    @ResponseBody
    public List<AmmunitionWeightDto> disableAmmunitionWeight(@RequestParam(name = "id") int id) {

        return this.ammunitionWeightService.delete(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("edit")
    @ResponseBody
    public AmmunitionWeightDto editWeight(@Valid @RequestBody AmmunitionWeightDto ammunitionWeightDto) {

        return this.ammunitionWeightService.update(ammunitionWeightDto);
    }
}
