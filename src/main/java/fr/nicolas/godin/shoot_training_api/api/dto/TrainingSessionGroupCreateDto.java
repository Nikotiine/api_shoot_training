package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.Positive;
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

    @Positive
    int totalShoots;

    @Positive
    float score;

    @Positive
    float horizontalGap;

    @Positive
    float verticalGap;
}