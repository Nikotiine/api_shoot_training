package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.ErrorMessage;
import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.dto.RegistrationDto;
import fr.nicolas.godin.shoot_training_api.api.dto.ValidationCodeDto;
import fr.nicolas.godin.shoot_training_api.api.service.RegistrationService;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Tag(name = "Registration",description = "Registration Controller")
@RequestMapping("/api")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private ModelMapper modelMapper;
    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationDto shooterEntityDto){

        try {

            Shooter shooter = this.modelMapper.map(shooterEntityDto, Shooter.class);
            this.registrationService.register(shooter);
            return ResponseEntity.status(CREATED).build();

        } catch (DataIntegrityViolationException e) {

            return  ResponseEntity.status(BAD_REQUEST).body(new ErrorMessage(1,"Email already use"));

        }

    }

    @GetMapping("authorize-validation/{email}")
    public ResponseEntity<?> emailVerification(@PathVariable("email") String email) {
        try {

            Boolean isAuthorize = this.registrationService.emailVerification(email);
            HttpStatus status = isAuthorize ? HttpStatus.OK : BAD_REQUEST;
            String message = isAuthorize ? "code is valid" : "code is out of time";
            return  ResponseEntity.status(status).body(message);

        } catch (NullPointerException e) {

            return ResponseEntity.status(BAD_REQUEST).body(new ErrorMessage(2,"Email is not valid"));

        }

    }

    @PostMapping("validation-code")
    public ResponseEntity<String> codeValidation(@Valid @RequestBody ValidationCodeDto code) {
        try {

            boolean accountActivated = this.registrationService.validationCode(code);
            HttpStatus status = accountActivated ? HttpStatus.OK : BAD_REQUEST;
            String message = accountActivated ? "code is valid" : "code is invalid please retry";
            return new ResponseEntity<>(message,status);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);

        }
    }

    @PostMapping("refresh-code")
    public ResponseEntity<?> refreshCode(@Valid @RequestBody RefreshCodeRequest refreshCodeRequest) {

       try {

            this.registrationService.refreshValidationCode(refreshCodeRequest);
            return ResponseEntity.ok().build();

        } catch (NullPointerException e) {

            return ResponseEntity.status(BAD_REQUEST).body(new ErrorMessage(2,"Email is not valid"));

        }


    }
}
