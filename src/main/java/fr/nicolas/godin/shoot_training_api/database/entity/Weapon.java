package fr.nicolas.godin.shoot_training_api.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"model","variation","factory_id"})
})
public class Weapon extends BaseEntity implements Serializable {

    @ManyToOne
    private WeaponFactory factory;

    @ManyToOne
    private WeaponType type;

    @ManyToOne
    private WeaponCategory category;

    @ManyToOne
    private Caliber caliber;

    private String model;

    private String variation;

    private double barrelLength;

    private boolean isHeavyBarrel;

    private double barrelStripes;


}
