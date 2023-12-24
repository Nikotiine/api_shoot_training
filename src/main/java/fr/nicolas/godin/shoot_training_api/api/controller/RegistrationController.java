package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.RegistrationDto;
import fr.nicolas.godin.shoot_training_api.api.dto.ValidationCodeDto;
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

    @GetMapping("authorize-validation/{email}")
    public ResponseEntity<String> emailVerification(@PathVariable("email") String email) {
        try {

            Boolean isAuthorize = this.registrationService.emailVerification(email);
            HttpStatus status = isAuthorize ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            String message = isAuthorize ? "code is valid" : "code is invalid";
            return new ResponseEntity<>(message,status);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("validation-code")
    public ResponseEntity<String> codeValidation(@Valid @RequestBody ValidationCodeDto code) {
        try {

            boolean accountActivated = this.registrationService.validationCode(code);
            HttpStatus status = accountActivated ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            String message = accountActivated ? "code is valid" : "code is invalid please retry";
            return new ResponseEntity<>(message,status);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
}
