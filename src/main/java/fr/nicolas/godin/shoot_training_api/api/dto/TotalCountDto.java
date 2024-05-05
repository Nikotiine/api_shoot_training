package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;

public record TotalCountDto(@NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE) long totalUsers,
                            @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE) long totalWeapons,
                            @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE) long totalOptics,
                            @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE) long totalAmmunition) {
}
