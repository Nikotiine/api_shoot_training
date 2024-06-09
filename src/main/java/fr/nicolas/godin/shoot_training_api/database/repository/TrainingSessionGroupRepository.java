package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.TrainingSessionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingSessionGroupRepository extends JpaRepository<TrainingSessionGroup, Integer> {
    void deleteAllByTrainingSessionId(int id);
    List<TrainingSessionGroup> findAllByTrainingSessionId(int id);

    @Query("SELECT tsg FROM TrainingSessionGroup tsg " +
            "JOIN tsg.trainingSession ts " +
            "JOIN ts.setup uws " +
            "JOIN uws.user u " +
            "WHERE u.id = :userId")
    List<TrainingSessionGroup> findByUserId(@Param("userId") int userId);
}
