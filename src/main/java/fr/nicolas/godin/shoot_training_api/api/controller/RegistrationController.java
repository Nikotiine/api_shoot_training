package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.RegistrationDto;
import fr.nicolas.godin.shoot_training_api.api.service.RegistrationService;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Registration",description = "Registration Controller")
@RequestMapping("/api")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private ModelMapper modelMapper;
    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationDto shooterEntityDto){

        try {

            Shooter shooter = this.modelMapper.map(shooterEntityDto, Shooter.class);
            this.registrationService.register(shooter);
            return new ResponseEntity<>("ok", HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

        }

    }
}
