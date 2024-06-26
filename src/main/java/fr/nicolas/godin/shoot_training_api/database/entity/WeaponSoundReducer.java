package fr.nicolas.godin.shoot_training_api.database.entity;

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
public class WeaponSoundReducer extends BaseEntity implements Serializable {
    @ManyToOne
    private Caliber caliber;

    private String model;

    private double length;

    private double diameter;

    private double weight;

    @ManyToOne
    private Factory factory;
}
