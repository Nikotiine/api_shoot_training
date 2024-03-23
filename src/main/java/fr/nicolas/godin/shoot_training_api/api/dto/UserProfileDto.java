package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.database.UserRole;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link User}
 */
@Getter
@Setter
@NoArgsConstructor

public class UserProfileDto implements Serializable {

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    int id;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    String firstName;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    String lastName;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @Email(message = DtoDecoratorConfiguration.INVALID_EMAIL_MESSAGE, regexp = DtoDecoratorConfiguration.EMAIL_REGEX)
    String email;

    UserRole role;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    Date createdAt;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    boolean isActive;

}