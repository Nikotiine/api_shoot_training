package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Ammunition}
 */
@Getter
@Setter
@NoArgsConstructor
public class AmmunitionDto extends NewAmmunitionDto implements Serializable {

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
     int id;

    boolean isActive;
    Date createdAt;

}
