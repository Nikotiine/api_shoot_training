package fr.nicolas.godin.shoot_training_api.api.dto;

import fr.nicolas.godin.shoot_training_api.database.entity.WeaponFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link WeaponFactory}
 */

@Getter
@Setter
@NoArgsConstructor
public class WeaponFactoryDto extends NewWeaponFactoryDto implements Serializable  {
    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    int id;




}