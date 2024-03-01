package fr.nicolas.godin.shoot_training_api.database.entity;

import fr.nicolas.godin.shoot_training_api.api.enums.FactoryType;
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
        @UniqueConstraint(columnNames = {"name","type"})
})
public class Factory extends BaseEntity implements Serializable {

    @OneToMany
    private Set<Weapon> weapons;

    @OneToMany
    private Set<Optics> optics;

    @OneToMany
    private Set<Ammunition> ammunitions;

    @OneToMany
    private Set<WeaponSoundReducer> weaponSoundReducers;

    private String name;

    private FactoryType type;
}
