package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import fr.nicolas.godin.shoot_training_api.database.entity.Caliber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AmmunitionWeightRepository extends JpaRepository<AmmunitionWeight, Integer> {

    List<AmmunitionWeight> findAmmunitionWeightByCalibersId(int id);

}