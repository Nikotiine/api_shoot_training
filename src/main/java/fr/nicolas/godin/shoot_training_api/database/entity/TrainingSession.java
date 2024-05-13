package fr.nicolas.godin.shoot_training_api.database.entity;

import fr.nicolas.godin.shoot_training_api.api.enums.TrainingPosition;
import fr.nicolas.godin.shoot_training_api.api.enums.WeaponSupport;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingSession extends BaseEntity implements Serializable {

    private Date date;

    private float temperature;

    @ManyToOne(optional = false)
    private UserWeaponSetup setup;

    private float distance;

    private float windSpeed;

    @ManyToOne(optional = false)
    private Ammunition ammunition;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<AmmunitionSpeedHistory> speedHistories;

    private float pressure;

    private TrainingPosition position;

    private WeaponSupport support;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<TrainingSessionGroup> trainingSessionGroups;

}
