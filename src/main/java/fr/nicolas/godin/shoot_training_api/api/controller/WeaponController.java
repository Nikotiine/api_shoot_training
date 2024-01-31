package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.NewWeaponDto;
import fr.nicolas.godin.shoot_training_api.api.dto.WeaponDataCollection;
import fr.nicolas.godin.shoot_training_api.api.dto.WeaponDto;
import fr.nicolas.godin.shoot_training_api.api.service.WeaponService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Weapon",description = "Weapon Controller")
@RequestMapping(value = "/api/weapon",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class WeaponController {

    private WeaponService weaponService;


    @PostMapping(value = "new")
    @ResponseBody
    public WeaponDto newWeapon(@Valid @RequestBody NewWeaponDto weaponDto){

        return this.weaponService.save(weaponDto);

    }

    @GetMapping(value = "data-collection")
    @ResponseBody
    public WeaponDataCollection getWeaponDataCollection(){

        return this.weaponService.getDataCollection();
    }

    @GetMapping(value = "all")
    @ResponseBody
    public List<WeaponDto> getAllWeapon(){

        return this.weaponService.getAll();
    }

}
