package fr.nicolas.godin.shoot_training_api.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingSessionGroup extends BaseEntity implements Serializable {

    private int totalShoots;

    private float score;

    private float horizontalGap;

    private float verticalGap;

    private float averageGap;

    @ManyToOne(optional = false)
    private TrainingSession trainingSession;
}
