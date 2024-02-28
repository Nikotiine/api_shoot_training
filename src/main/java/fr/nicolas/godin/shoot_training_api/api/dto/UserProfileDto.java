package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.database.UserRole;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
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
    int id;
    String firstName;
    String lastName;
    String email;
    UserRole role;
    Date createdAt;
    boolean isActive;

}