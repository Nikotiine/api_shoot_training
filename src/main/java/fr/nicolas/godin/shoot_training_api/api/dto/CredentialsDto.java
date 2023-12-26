package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Shooter}
 */
@Value
public class CredentialsDto implements Serializable {
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    @NotBlank
    String email;
    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    String password;
}