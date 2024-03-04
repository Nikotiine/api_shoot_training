package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.UserWeaponSetup}
 */
@Getter
@Setter
@NoArgsConstructor
public class UserWeaponSetupCreateDto implements Serializable {

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    WeaponDto weapon;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    OpticsDto optics;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    UserProfileDto user;

    WeaponSoundReducerDto soundReducer;

    @PositiveOrZero(message = DtoDecoratorConfiguration.POSITIVE_OR_ZERO_MESSAGE)
    int slopeRail;

}