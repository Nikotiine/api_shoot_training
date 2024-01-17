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
@RequestMapping("/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private ModelMapper modelMapper;
    @PostMapping(value="register",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseMessage> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto){

        User user = this.modelMapper.map(userRegistrationDto, User.class);
        this.registrationService.register(user);
        return ResponseEntity.status(CREATED).body(new ResponseMessage(CodeMessageResponse.REGISTER_SUCCESS));

    }


    @PostMapping(value ="validation-code",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> codeValidation(@Valid @RequestBody ActivationCodeDto activationCodeDto) {
        try {

            CodeMessageResponse codeMessageResponse = CodeMessageResponse.ACCOUNT_IS_ALREADY_ACTIVE;
            HttpStatus status = BAD_REQUEST;
            boolean accountIsActive = this.registrationService.accountIsAlreadyActivated(activationCodeDto.getEmail());

            if (!accountIsActive) {

                boolean isInValidityTime = this.registrationService.emailVerification(activationCodeDto.getEmail());

                if (isInValidityTime) {

                    boolean accountActivated = this.registrationService.validationCode(activationCodeDto);

                    if (accountActivated) {

                        status = HttpStatus.OK;
                        codeMessageResponse = CodeMessageResponse.ACCOUNT_ACTIVATED;

                    } else {

                        codeMessageResponse = CodeMessageResponse.BAD_ACTIVATION_CODE;
                    }


                }else {

                    codeMessageResponse = CodeMessageResponse.CODE_IS_OUT_OF_TIME;


                }
            }


            return  ResponseEntity.status(status).body(new ResponseMessage(codeMessageResponse));

        } catch (NullPointerException e) {

            return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(CodeMessageResponse.EMAIL_IS_INVALID));

        }
    }

    @PostMapping(value ="refresh-code",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> refreshCode(@Valid @RequestBody RefreshCodeRequest request) {

       try {

           this.registrationService.refreshValidationCode(request);
           return ResponseEntity.status(OK).body(new ResponseMessage(CodeMessageResponse.NEW_CODE_SENT));

        } catch (NullPointerException e) {


           return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(CodeMessageResponse.EMAIL_IS_INVALID));

        }


    }
}
