package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Ammunition}
 */
@Getter
@Setter
@NoArgsConstructor
public class NewAmmunitionDto implements Serializable {

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    CaliberDto caliber;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    AmmunitionFactoryDto factory;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    AmmunitionWeightDto weight;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    String name;

    @Positive
    int initialSpeed;

    @Positive
    double ballisticCoefficient;
}