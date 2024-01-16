package fr.nicolas.godin.shoot_training_api.database.entity;

import fr.nicolas.godin.shoot_training_api.database.ActivationCodeType;
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
public class ActivationCode extends BaseEntity implements Serializable {

    @Column
    private int code;

    @Column
    private Date timeOfValidity;

    @OneToOne
    private User user;

    @Column
    private ActivationCodeType type;
}
