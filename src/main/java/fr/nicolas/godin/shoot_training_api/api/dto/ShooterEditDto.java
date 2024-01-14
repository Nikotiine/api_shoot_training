package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Shooter}
 */
@Value
public class ShooterEditDto extends ShooterProfileDto implements Serializable  {


    String oldPassword;
    String password;
}