package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RefreshCodeRequest(
        @Email(message = "Not valid email format", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @NotNull(message = "Can not be null")
        @NotEmpty(message = "Can not be empty")
        @NotBlank(message = "Can not be blank")
        String email
        ) {}
