package fr.nicolas.godin.shoot_training_api.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link fr.nicolas.godin.shoot_training_api.database.entity.Optics}
 */
@Getter
@Setter
@NoArgsConstructor
public class NewOpticsDto implements Serializable {

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    OpticsFactoryDto factory;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    OpticsBodyDiameterDto bodyDiameter;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    OpticsOutletDiameterDto outletDiameter;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    OpticsFocalPlaneDto focalPlane;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    OpticsUnitDto opticsUnit;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    String name;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    @Positive
    int minZoom;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    @Positive
    int maxZoom;

    boolean parallax;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    @Positive
    int minParallax;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    @Positive
    double maxElevation;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    @Positive
    double maxDerivation;

    @NotNull(message =  DtoDecoratorConfiguration.NOT_NULL_MESSAGE)
    @NotEmpty(message = DtoDecoratorConfiguration.NOT_EMPTY_MESSAGE)
    @NotBlank(message = DtoDecoratorConfiguration.NOT_BLACK_MESSAGE)
    @Positive
    double valueOfOneClick;
}