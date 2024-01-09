package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RefreshCodeRequest(
        @Email(message = DtoDecoratorConfiguration.INVALID_EMAIL_MESSAGE, regexp = DtoDecoratorConfiguration.EMAIL_REGEX)
        @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
        @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
        @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
        String email
        ) {}
