package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.NewPasswordRequestDto;
import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.dto.ResponseMessage;
import fr.nicolas.godin.shoot_training_api.api.enums.CodeMessageResponse;
import fr.nicolas.godin.shoot_training_api.api.service.ForgotPasswordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        this.forgotPasswordService.save(newPasswordRequestDto);
        return ResponseEntity.status(OK).body(new ResponseMessage(CodeMessageResponse.NEW_PASSWORD_CHANGE));


    }
}
