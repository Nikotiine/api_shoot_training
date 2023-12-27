package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.database.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Shooter}
 */
@Getter
@Setter
@NoArgsConstructor

public class ShooterProfileDto implements Serializable {
    int id;
    String firstName;
    String lastName;
    String email;
    UserRole role;

}