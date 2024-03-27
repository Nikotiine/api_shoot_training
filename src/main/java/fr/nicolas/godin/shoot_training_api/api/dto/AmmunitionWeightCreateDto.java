package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight}
 */
@Getter
@Setter
@NoArgsConstructor
public class AmmunitionWeightCreateDto implements Serializable {
    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @Positive
    double grains;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @Positive
    double grams;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    Set<CaliberDto> calibers;
}