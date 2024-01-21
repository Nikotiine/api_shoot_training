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

    @ManyToOne(optional = false)
    private WeaponFactory factory;

    @ManyToOne(optional = false)
    private WeaponType type;

    @ManyToOne(optional = false)
    private WeaponCategory category;

    @ManyToOne(optional = false)
    private Caliber caliber;

    @Column(nullable = false)
    private String model;

    private String variation;

    @Column(nullable = false)
    private double barrelLength;

    @Column(columnDefinition = "boolean default false")
    private boolean isHeavyBarrel = false;

    private double barrelStripes;


}
