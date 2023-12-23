package fr.nicolas.godin.shoot_training_api.database.entity;

import fr.nicolas.godin.shoot_training_api.database.repository.ShooterRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ValidationCode extends BaseEntity implements Serializable {

    @Column
    private int code;

    @Column
    private Date timeOfValidity;

    @OneToOne
    private Shooter shooter;
}
