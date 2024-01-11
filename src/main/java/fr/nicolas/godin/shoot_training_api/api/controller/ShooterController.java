package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.dto.ResponseMessage;
import fr.nicolas.godin.shoot_training_api.api.enums.CodeMessageResponse;
import fr.nicolas.godin.shoot_training_api.api.service.ShooterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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

            this.shooterService.sendCodeForNewPassword(request);
            return ResponseEntity.status(OK).body(new ResponseMessage(CodeMessageResponse.RESET_PASSWORD_CODE_SENT));

        } catch (NullPointerException e) {

            return ResponseEntity.status(BAD_REQUEST).body(new ResponseMessage(CodeMessageResponse.EMAIL_IS_INVALID));

        }

    }
}
