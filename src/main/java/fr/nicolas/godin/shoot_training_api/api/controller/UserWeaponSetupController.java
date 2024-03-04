package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.UserWeaponSetupCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.UserWeaponSetupDto;
import fr.nicolas.godin.shoot_training_api.api.service.UserSetupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Weapon Setup",description = "User Weapon Setup Controller")
@RequestMapping(value = "/api/setup",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserWeaponSetupController {

    private UserSetupService userSetupService;

    @PostMapping("new")
    public UserWeaponSetupDto newSetup(@Valid @RequestBody UserWeaponSetupCreateDto userWeaponSetupCreateDto) {

       return this.userSetupService.create(userWeaponSetupCreateDto);

    }

    @GetMapping("all")
    public List<UserWeaponSetupDto> getAllUserWeaponSetup(@RequestParam(name = "id") int userId){
        return this.userSetupService.getAllActive(userId);
    }

}
