package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.service.CaliberService;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponCategoryService;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponFactoryService;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponService;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponTypeService;
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
    private WeaponFactoryService weaponFactoryService;
    private WeaponTypeService weaponTypeService;
    private WeaponCategoryService weaponCategoryService;
    private CaliberService caliberService;


    @PostMapping(value = "save/weapon")
    @ResponseBody
    public WeaponDto newWeapon(@Valid @RequestBody NewWeaponDto weaponDto){

        return this.weaponService.create(weaponDto);

    }

    @PostMapping(value = "save/factory")
    @ResponseBody
    public WeaponFactoryDto newFactory(@Valid @RequestBody NewWeaponFactoryDto newWeaponFactory){
        return this.weaponFactoryService.create(newWeaponFactory);
    }

    @GetMapping(value = "data-collection")
    @ResponseBody
    public WeaponDataCollection getWeaponDataCollection(){

        return new WeaponDataCollection(
                this.weaponFactoryService.getAllActive(),
                this.weaponTypeService.getAll(),
                this.weaponCategoryService.getAll(),
                this.caliberService.getAllActive()
        );
    }

    @GetMapping(value = "all")
    @ResponseBody
    public List<WeaponDto> getAllWeapon(){

        return this.weaponService.getAllActive();
    }

}
