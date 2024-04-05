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
public class AmmunitionSpeedHistory extends BaseEntity implements Serializable {

    private float speed;

    @ManyToOne(optional = false)
    private Ammunition ammunition;

    @ManyToOne(optional = false)
    private Weapon weapon;

    @ManyToOne(optional = false)
    private TrainingSession trainingSession;
}
