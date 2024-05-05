package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;

public record LastEntriesDto(
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE) UserProfileDto lastUser,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE) WeaponDto lastWeapon,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE) OpticsDto lastOptics,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE) AmmunitionDto lastAmmunition
         ) {
}
