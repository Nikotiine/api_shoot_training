package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record WeaponDataCollection(
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<FactoryDto> weaponFactoryList,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<WeaponTypeDto> weaponTypeList,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<WeaponCategoryDto> weaponCategoryList,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<CaliberDto> caliberList) {
}
