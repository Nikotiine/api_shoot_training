package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession,Integer> {

    List<TrainingSession> findAllByActiveIsTrue();


    @Query("SELECT ts FROM TrainingSession ts " +
            "JOIN ts.setup uws " +
            "JOIN uws.user u " +
            "WHERE u.id = :userId and ts.active = true ORDER BY ts.createdAt ASC "

            )
    List<TrainingSession> findTrainingSessionsByUserIdAndActiveIsTrueOrderByCreatedAtAsc(@Param("userId") int userId);

    TrainingSession findTrainingSessionById(int id);


    @Query("SELECT ts FROM TrainingSession ts " +
            "JOIN ts.setup uws " +
            "JOIN uws.user u " +
            "WHERE u.id = :userId ORDER BY ts.createdAt ASC "

    )
    List<TrainingSession> findTrainingSessionsByUserIdAndOrderByCreatedAtAsc(@Param("userId") int userId);
}
