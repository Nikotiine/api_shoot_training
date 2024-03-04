package fr.nicolas.godin.shoot_training_api.api.dto;

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
    double grains;
    double grams;
    Set<CaliberDto> calibers;
}