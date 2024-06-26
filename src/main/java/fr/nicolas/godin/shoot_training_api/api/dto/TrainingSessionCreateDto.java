package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.api.enums.TrainingPosition;
import fr.nicolas.godin.shoot_training_api.api.enums.WeaponSupport;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.TrainingSession}
 */
@Getter
@Setter
@NoArgsConstructor
public class TrainingSessionCreateDto implements Serializable {

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    Date date;

    float temperature;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    UserWeaponSetupDto setup;

    @Positive
    float distance;

    float windSpeed;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    AmmunitionDto ammunition;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    Set<AmmunitionSpeedHistoryCreateDto> speedHistories;

    float pressure;

    TrainingPosition position;

    WeaponSupport support;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    Set<TrainingSessionGroupCreateDto> trainingSessionGroups;
}