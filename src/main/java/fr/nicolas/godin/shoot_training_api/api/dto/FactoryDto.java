package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Factory}
 */
@Getter
@Setter
@NoArgsConstructor
public class FactoryDto extends NewFactoryDto {
    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    int id;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    Date createdAt;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    boolean active;

}
