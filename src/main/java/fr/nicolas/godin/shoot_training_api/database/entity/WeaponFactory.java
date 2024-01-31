package fr.nicolas.godin.shoot_training_api.database.entity;

import jakarta.persistence.Column;
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
public class WeaponFactory extends BaseEntity implements Serializable {

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "factory")
    private Set<Weapon> weapons;
}
