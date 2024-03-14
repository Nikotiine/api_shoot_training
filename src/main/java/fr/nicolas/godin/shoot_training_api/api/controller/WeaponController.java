package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.FactoryType;
import fr.nicolas.godin.shoot_training_api.api.service.CaliberService;
import fr.nicolas.godin.shoot_training_api.api.service.FactoryService;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponCategoryService;
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
    private FactoryService factoryService;
    private WeaponTypeService weaponTypeService;
    private WeaponCategoryService weaponCategoryService;
    private CaliberService caliberService;


    @PostMapping(value = "save")
    @ResponseBody
    public WeaponDto newWeapon(@Valid @RequestBody WeaponCreateDto weaponDto){

        return this.weaponService.create(weaponDto);

    }



    @GetMapping(value = "data-collection")
    @ResponseBody
    public WeaponDataCollection getWeaponDataCollection(){

        return new WeaponDataCollection(
                this.factoryService.getAllByType(FactoryType.WEAPON),
                this.weaponTypeService.getAll(),
                this.weaponCategoryService.getAll(),
                this.caliberService.getAllActive()
        );
    }

    @GetMapping(value = "active")
    @ResponseBody
    public List<WeaponDto> getActiveAllWeapon(){

        return this.weaponService.getAllActive();
    }

    @GetMapping(value = "all")
    @ResponseBody
    public List<WeaponDto> getAllWeapon(){

        return this.weaponService.getAll();
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public List<WeaponDto> disableWeapon(@Valid @RequestBody WeaponDto weaponDto) {

        return this.weaponService.delete(weaponDto);
    }

    @PutMapping(value = "edit")
    @ResponseBody
    public WeaponDto editWeapon(@Valid @RequestBody WeaponDto weaponDto) {

        return this.weaponService.update(weaponDto);
    }
}
