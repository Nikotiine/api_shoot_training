package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponCategoryService;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponService;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "API_Weapon",description = "Weapon Controller")
@RequestMapping(value = "/api/weapon",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class WeaponController {

    private WeaponService weaponService;
    private WeaponTypeService weaponTypeService;
    private WeaponCategoryService weaponCategoryService;



    @PostMapping(value = "save")
    @ResponseBody
    public WeaponDto newWeapon(@Valid @RequestBody WeaponCreateDto weaponDto){

        return this.weaponService.create(weaponDto);

    }


    @GetMapping(value = "all-types")
    @ResponseBody
    public List<WeaponTypeDto> getAllActiveType(){
        return this.weaponTypeService.getAll();
    }

    @GetMapping(value = "all-categories")
    @ResponseBody
    public List<WeaponCategoryDto> getAllActiveCategories(){
        return this.weaponCategoryService.getAll();
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "delete")
    @ResponseBody
    public List<WeaponDto> disableWeapon(@RequestParam(name = "id") int id) {

        return this.weaponService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "edit")
    @ResponseBody
    public WeaponDto editWeapon(@Valid @RequestBody WeaponDto weaponDto) {

        return this.weaponService.update(weaponDto);
    }
}
