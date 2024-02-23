package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import fr.nicolas.godin.shoot_training_api.database.entity.Caliber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AmmunitionWeightRepository extends CrudRepository<AmmunitionWeight, Integer> {

    Iterable<AmmunitionWeight> findAmmunitionWeightByCalibersId(int id);

}