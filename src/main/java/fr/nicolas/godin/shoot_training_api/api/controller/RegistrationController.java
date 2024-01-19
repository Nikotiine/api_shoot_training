package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.CodeMessageResponse;
import fr.nicolas.godin.shoot_training_api.api.service.RegistrationService;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
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
@RequestMapping(value="/api/registration",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private ModelMapper modelMapper;
    @PostMapping(value="register")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseMessage> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto){

        User user = this.modelMapper.map(userRegistrationDto, User.class);
        this.registrationService.register(user);
        return ResponseEntity.status(CREATED).body(new ResponseMessage(CodeMessageResponse.REGISTER_SUCCESS));

    }


    @PostMapping(value ="validation-code")
    public ResponseEntity<ResponseMessage> codeValidation(@Valid @RequestBody ActivationCodeDto activationCodeDto) {

        this.registrationService.validationCode(activationCodeDto);
        return  ResponseEntity.status(OK).body(new ResponseMessage(CodeMessageResponse.ACCOUNT_ACTIVATED));

    }

    @PostMapping(value ="refresh-code")
    public ResponseEntity<ResponseMessage> refreshCode(@Valid @RequestBody RefreshCodeRequest request) {

        this.registrationService.refreshValidationCode(request);
        return ResponseEntity.status(OK).body(new ResponseMessage(CodeMessageResponse.NEW_CODE_SENT));

    }
}
