package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.api.enums.CodeMessageResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class ResponseMessage {
    @NotNull
    int code;

    @NotNull
    @NotEmpty
    String message;
    public ResponseMessage(CodeMessageResponse response) {
        this.code = response.getCode();
        this.message = response.getMessage();
    }
}
