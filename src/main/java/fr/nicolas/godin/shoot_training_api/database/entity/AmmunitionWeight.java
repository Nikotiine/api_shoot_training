package fr.nicolas.godin.shoot_training_api.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
public class AmmunitionWeight extends BaseEntity implements Serializable {

    private double grains;

    private double grams;

    @ManyToMany
    private Set<Caliber> calibers;


}
