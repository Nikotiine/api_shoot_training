package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionSpeedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmmunitionSpeedHistoryRepository extends JpaRepository<AmmunitionSpeedHistory,Integer> {
}
