package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import fr.nicolas.godin.shoot_training_api.database.entity.Caliber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmmunitionWeightRepository extends JpaRepository<AmmunitionWeight, Integer> {

    Iterable<AmmunitionWeight> findAmmunitionWeightByCalibersId(int id);
}