package fr.nicolas.godin.shoot_training_api.database.entity;

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
public class OpticsFocalPlane extends BaseEntity implements Serializable {

    @OneToMany(mappedBy = "focalPlane")
    private Set<Optics> optics;

    @Column(unique = true)
    private String label;
}
