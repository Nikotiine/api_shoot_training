package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.TrainingSessionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingSessionGroupRepository extends JpaRepository<TrainingSessionGroup, Integer> {
}
