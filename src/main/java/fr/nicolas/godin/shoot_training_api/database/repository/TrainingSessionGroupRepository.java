package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.TrainingSessionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingSessionGroupRepository extends JpaRepository<TrainingSessionGroup, Integer> {
    void deleteAllByTrainingSessionId(int id);
    List<TrainingSessionGroup> findAllByTrainingSessionId(int id);
}
