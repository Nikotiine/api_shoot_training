package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.NewWeaponDto;
import fr.nicolas.godin.shoot_training_api.api.dto.ResponseMessage;
import fr.nicolas.godin.shoot_training_api.api.dto.WeaponDataCollection;
import fr.nicolas.godin.shoot_training_api.api.service.WeaponService;
import fr.nicolas.godin.shoot_training_api.database.entity.Weapon;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Weapon",description = "Weapon Controller")
@RequestMapping(value = "/api/weapon",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class WeaponController {

    private WeaponService weaponService;
    private ModelMapper modelMapper;

    @PostMapping(value = "new")
    @ResponseBody
    public Weapon newWeapon(@Valid @RequestBody NewWeaponDto newWeaponDto){
        Weapon newWeapon = this.modelMapper.map(newWeaponDto,Weapon.class);
        return this.weaponService.save(newWeapon);

    }

    @GetMapping(value = "data-collection")
    @ResponseBody
    public WeaponDataCollection getDataCollection(){
        //this.weaponService.test();
       // this.weaponService.getDataCollection();
        return this.weaponService.getDataCollection();
    }
}
