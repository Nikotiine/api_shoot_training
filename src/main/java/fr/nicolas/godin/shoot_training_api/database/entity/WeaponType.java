package fr.nicolas.godin.shoot_training_api.database.entity;
import fr.nicolas.godin.shoot_training_api.api.enums.WeaponTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class WeaponType extends BaseEntity implements Serializable {

    @OneToMany(mappedBy = "type")
    private Set<Weapon> weapons;

    @Column(unique = true)
    private String label;

    @Column()
    private WeaponTypes type = WeaponTypes.RIFFLE;
}
