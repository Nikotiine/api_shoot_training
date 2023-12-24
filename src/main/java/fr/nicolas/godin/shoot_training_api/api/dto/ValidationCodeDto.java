package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
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
    String shooterEmail;
}