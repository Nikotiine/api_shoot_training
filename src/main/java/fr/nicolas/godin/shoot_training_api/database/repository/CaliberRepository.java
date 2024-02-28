package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Caliber;
import org.springframework.data.repository.CrudRepository;

public interface CaliberRepository extends CrudRepository<Caliber,Integer> {
    Caliber findFirstByOrderByIdDesc();
}
