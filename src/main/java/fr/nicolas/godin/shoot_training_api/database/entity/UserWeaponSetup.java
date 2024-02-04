package fr.nicolas.godin.shoot_training_api.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class UserWeaponSetup extends BaseEntity implements Serializable {

    @ManyToOne
    private Weapon weapon;

    @ManyToOne
    private Optics optics;

    @ManyToOne
    private User user;

    @ManyToOne
    private WeaponSoundReducer soundReducer;

    private int slopeRail = 0;
}
