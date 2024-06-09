package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.TrainingSessionGroup}
 */
@Getter
@Setter
@NoArgsConstructor
public class TrainingSessionGroupCreateDto implements Serializable {

    @PositiveOrZero
    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    int totalShoots;

    @PositiveOrZero
    float score;

    @PositiveOrZero
    float horizontalGap;

    @PositiveOrZero
    float verticalGap;

    @PositiveOrZero
    float averageGap;



}