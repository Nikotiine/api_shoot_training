package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionSpeedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmmunitionSpeedHistoryRepository extends JpaRepository<AmmunitionSpeedHistory,Integer> {
    List<AmmunitionSpeedHistory> findAllByTrainingSessionId(int id);
    void deleteAllByTrainingSessionId(int id);
}
