package fr.nicolas.godin.shoot_training_api.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Optics extends BaseEntity implements Serializable {


    @ManyToOne
    private OpticsFactory factory;

    @ManyToOne
    private OpticsBodyDiameter bodyDiameter;

    @ManyToOne
    private OpticsOutletDiameter outletDiameter;

    @ManyToOne
    private OpticsFocalPlane focalPlane;

    @ManyToOne
    private OpticsClickType clickType;

    private String name;

    private int minZoom;

    private int maxZoom;

    private boolean parallax;

    @Column(nullable = true)
    private int minParallax;

    private double maxElevation;

    private double maxDerivation;

    private double valueOfOneClick;
}
