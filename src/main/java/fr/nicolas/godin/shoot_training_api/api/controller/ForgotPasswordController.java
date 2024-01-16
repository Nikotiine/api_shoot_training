package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.NewPasswordRequestDto;
import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.dto.ResponseMessage;
import fr.nicolas.godin.shoot_training_api.api.enums.CodeMessageResponse;
import fr.nicolas.godin.shoot_training_api.api.service.ForgotPasswordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(name = "ForgotPassword",description = "ForgotPassword Controller")
@RequestMapping("/api/forgot-password")
@AllArgsConstructor
public class ForgotPasswordController {
    private ForgotPasswordService forgotPasswordService;

    @PostMapping(value="request-new-password",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> requestNewPassword(@RequestBody RefreshCodeRequest request) {


            this.forgotPasswordService.requestValidationCodeForNewPassword(request);
            return ResponseEntity.status(OK).body(new ResponseMessage( CodeMessageResponse.RESET_PASSWORD_CODE_SENT));


    }

    @PostMapping(value="save-new-password",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> saveNewPassword(@RequestBody NewPasswordRequestDto newPasswordRequestDto) {
        try {

            String email = newPasswordRequestDto.getEmail();
            HttpStatus status = BAD_REQUEST;
            CodeMessageResponse codeMessageResponse = CodeMessageResponse.CODE_IS_OUT_OF_TIME;
            boolean isInValidityTime = this.forgotPasswordService.emailVerificationAndValidityCode(email);

            if (isInValidityTime) {

                boolean isPasswordChange = this.forgotPasswordService.changePassword(newPasswordRequestDto);

                if (!isPasswordChange) {
                    codeMessageResponse = CodeMessageResponse.BAD_ACTIVATION_CODE;
                } else {
                    codeMessageResponse = CodeMessageResponse.NEW_PASSWORD_CHANGE;
                    status = OK;
                }

            }

            return ResponseEntity.status(status).body(new ResponseMessage(codeMessageResponse));

        }catch (NullPointerException e){

            return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(CodeMessageResponse.EMAIL_IS_INVALID));

        }
    }
}
