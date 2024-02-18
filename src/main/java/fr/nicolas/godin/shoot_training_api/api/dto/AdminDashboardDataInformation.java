package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;




public record AdminDashboardDataInformation(
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)long totalUsers,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)UserProfileDto lastUserEntry,

        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)long totalWeapons,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)WeaponDto lastWeaponEntry,

        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)long totalOptics,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)OpticsDto lastOpticEntry,

        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)long totalAmmunition,
        AmmunitionDto lastAmmunitionEntry) {


}
