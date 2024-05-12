package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionSpeedHistory}
 */
@Getter
@Setter
@NoArgsConstructor
public class AmmunitionSpeedHistoryDto extends AmmunitionSpeedHistoryCreateDto implements Serializable{
    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    int id;
    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    Date createdAt;
}