package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AmmunitionDataCollection(
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<CaliberDto> caliberList,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<AmmunitionFactoryDto> factoryList){
}
