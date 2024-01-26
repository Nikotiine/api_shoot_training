package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OpticsDataCollection(
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<OpticsFactoryDto> opticsFactoryList,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<OpticsBodyDiameterDto> opticsBodyDiameterList,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<OpticsClickTypeDto> opticsClickTypeList,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<OpticsFocalPlaneDto> opticsFocalPlaneList,
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)List<OpticsOutletDiameterDto> opticsOutletDiameterList) {
}
