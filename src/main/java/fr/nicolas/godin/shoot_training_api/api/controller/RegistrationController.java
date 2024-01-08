package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.ResponseMessage;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@Tag(name = "Registration",description = "Registration Controller")
@RequestMapping("/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private ModelMapper modelMapper;
    @PostMapping(value="register",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseMessage> register(@Valid @RequestBody RegistrationDto shooterEntityDto){

        try {

            Shooter shooter = this.modelMapper.map(shooterEntityDto, Shooter.class);
            this.registrationService.register(shooter);
            return ResponseEntity.status(CREATED).body(new ResponseMessage(1,"Please active your account"));

        } catch (DataIntegrityViolationException e) {

            return  ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(101,"Email already use"));

        }

    }


    @PostMapping(value ="validation-code",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> codeValidation(@Valid @RequestBody ValidationCodeDto validationCodeDto) {
        try {
            int code = 105;
            HttpStatus status =  BAD_REQUEST;
            String message = "Account is already active";
            boolean accountIsActive = this.registrationService.accountIsAlreadyActivated(validationCodeDto.getShooterEmail());
            if (!accountIsActive){

                boolean isInValidityTime = this.registrationService.emailVerification(validationCodeDto.getShooterEmail());

                if (isInValidityTime){

                    boolean accountActivated = this.registrationService.validationCode(validationCodeDto);
                    status = accountActivated ? HttpStatus.OK : BAD_REQUEST;
                    code = accountActivated ? 3 : 103;
                    message = accountActivated ? "code is valid / account active" : "code is invalid please retry";

                }else {

                    code = 102;
                    message = "Code is out of time";

                }
            }


            return  ResponseEntity.status(status).body(new ResponseMessage(code,message));

        } catch (NullPointerException e) {

            return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(100,"Email is not valid"));

        }
    }

    @PostMapping(value ="refresh-code",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> refreshCode(@Valid @RequestBody RefreshCodeRequest refreshCodeRequest) {

       try {

            this.registrationService.refreshValidationCode(refreshCodeRequest);
            return ResponseEntity.status(OK).body(new ResponseMessage(4,"New code is send"));

        } catch (NullPointerException e) {

            return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(100,"Email is not valid"));

        }


    }
}
