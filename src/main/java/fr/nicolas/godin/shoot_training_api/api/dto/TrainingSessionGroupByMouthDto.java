package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;


public record TrainingSessionGroupByMouthDto(
        @NotNull(message = DtoDecoratorConfiguration.NOT_NULL_MESSAGE) Map<Integer, List<TrainingSessionDto>> groupByMouth) {
}
