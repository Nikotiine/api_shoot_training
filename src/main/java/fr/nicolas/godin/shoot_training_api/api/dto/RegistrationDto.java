package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Shooter}
 */
@Value
public class RegistrationDto implements Serializable {
    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    String firstName;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    String lastName;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    String email;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    String password;
}