package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Weapon}
 */
@Getter
@Setter
@NoArgsConstructor
public class WeaponDto extends NewWeaponDto implements Serializable {
    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    private int id;

}
