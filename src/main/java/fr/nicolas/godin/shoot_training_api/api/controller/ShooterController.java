package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.NewPasswordRequestDto;
import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.dto.ResponseMessage;
import fr.nicolas.godin.shoot_training_api.api.enums.CodeMessageResponse;
import fr.nicolas.godin.shoot_training_api.api.service.ShooterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(name = "Shooter",description = "Shooter Controller")
@RequestMapping("/api/shooter")
@AllArgsConstructor
public class ShooterController {
    private ShooterService shooterService;

    @PostMapping(value="forgot-password",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> forgotPassword(@RequestBody RefreshCodeRequest request) {
        try {

            boolean codeIsAlreadySend = this.shooterService.codeIsAlreadySend(request.email());
            CodeMessageResponse codeMessageResponse = CodeMessageResponse.CODE_IS_ALREADY_SEND;
            HttpStatus status = BAD_REQUEST;
            if (!codeIsAlreadySend){
                this.shooterService.sendCodeForNewPassword(request);
                codeMessageResponse = CodeMessageResponse.RESET_PASSWORD_CODE_SENT;
                status = OK;
            }

            return ResponseEntity.status(status).body(new ResponseMessage(codeMessageResponse));

        } catch (NullPointerException e) {

            return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(CodeMessageResponse.EMAIL_IS_INVALID_OR_ACCOUNT_INACTIVE));

        }

    }

    @PostMapping(value="new-password",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> newPassword(@RequestBody NewPasswordRequestDto newPasswordRequestDto) {
        try {

            String email = newPasswordRequestDto.getEmail();
            HttpStatus status = BAD_REQUEST;
            CodeMessageResponse codeMessageResponse = CodeMessageResponse.CODE_IS_OUT_OF_TIME;
            boolean isInValidityTime = this.shooterService.emailVerificationAndValidityCode(email);

            if (isInValidityTime) {

                boolean isPasswordChange = this.shooterService.changePassword(newPasswordRequestDto);

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
