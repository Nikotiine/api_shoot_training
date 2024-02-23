package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
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


    @PostMapping(value = "save/weapon")
    @ResponseBody
    public WeaponDto newWeapon(@Valid @RequestBody NewWeaponDto weaponDto){

        return this.weaponService.save(weaponDto);

    }

    @PostMapping(value = "save/factory")
    @ResponseBody
    public WeaponFactoryDto newFactory(@Valid @RequestBody NewWeaponFactoryDto newWeaponFactory){
        return this.weaponService.saveNewFactory(newWeaponFactory);
    }

    @GetMapping(value = "data-collection")
    @ResponseBody
    public WeaponDataCollection getWeaponDataCollection(){

        return this.weaponService.getDataCollection();
    }

    @GetMapping(value = "all")
    @ResponseBody
    public List<WeaponDto> getAllWeapon(){

        return this.weaponService.getAllActive();
    }

}
