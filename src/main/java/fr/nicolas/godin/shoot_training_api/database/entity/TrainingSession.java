package fr.nicolas.godin.shoot_training_api.database.entity;

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

    private  Date date;

    private float temperature;

    @ManyToOne(optional = false)
    private UserWeaponSetup setup;

    private float distance;

    private float windSpeed;

    @ManyToOne(optional = false)
    private Ammunition ammunition;

    @OneToMany
    private Set<AmmunitionSpeedHistory> speedHistories;
}
