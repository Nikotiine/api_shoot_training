package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionDto;
import fr.nicolas.godin.shoot_training_api.api.dto.WeaponDto;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionSpeedHistory;
import fr.nicolas.godin.shoot_training_api.database.entity.TrainingSession;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link AmmunitionSpeedHistory}
 */
@Getter
@Setter
@NoArgsConstructor
public class AmmunitionSpeedHistoryCreateDto implements Serializable {

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @Positive
    float speed;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    AmmunitionDto ammunition;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    WeaponDto weapon;

    TrainingSession trainingSession;
}