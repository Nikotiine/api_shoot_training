package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Weapon}
 */
@Getter
@Setter
@NoArgsConstructor
public class NewWeaponDto implements Serializable {
    WeaponFactoryDto factory;

    WeaponTypeDto type;

    WeaponCategoryDto category;

    CaliberDto caliber;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    String model;

    String variation;

    @Positive
    double barrelLength;

    boolean isHeavyBarrel;

    double barrelStripes;
}