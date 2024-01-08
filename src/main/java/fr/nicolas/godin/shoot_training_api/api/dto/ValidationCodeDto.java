package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.ValidationCode}
 */
@Value
public class ValidationCodeDto implements Serializable {
    @Min(100000)
    @Max(999999)
    @Positive
    int code;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    String shooterEmail;
}