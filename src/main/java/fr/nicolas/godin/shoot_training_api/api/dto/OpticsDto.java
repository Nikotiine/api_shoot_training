package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpticsDto extends NewOpticsDto{

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    int id;

}
