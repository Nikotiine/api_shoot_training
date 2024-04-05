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
        @UniqueConstraint(columnNames = {"name","factory_id"})
})
public class Ammunition extends BaseEntity implements Serializable {

    @ManyToOne(optional = false)
    private Caliber caliber;

    @ManyToOne(optional = false)
    private Factory factory;

    @ManyToOne(optional = false)
    private AmmunitionWeight weight;

    private String name;

    private int initialSpeed;

    private double ballisticCoefficient;

    @OneToMany
    private Set<AmmunitionSpeedHistory> speedHistories;

}
