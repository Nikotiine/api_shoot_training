package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ActivationCode}
 */
@Value
public class ActivationCodeDto implements Serializable {

    @Min(DtoDecoratorConfiguration.MIN_LENGTH_CODE)
    @Max(DtoDecoratorConfiguration.MAX_LENGTH_CODE)
    @Positive
    int code;

    @Email(message = DtoDecoratorConfiguration.INVALID_EMAIL_MESSAGE, regexp = DtoDecoratorConfiguration.EMAIL_REGEX)
    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    String email;
}