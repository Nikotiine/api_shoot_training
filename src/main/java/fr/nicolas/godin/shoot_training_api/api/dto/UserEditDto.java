package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.database.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Getter
@Setter
@NoArgsConstructor
public class UserEditDto extends UserProfileDto implements Serializable  {


    String oldPassword;
    String password;
}