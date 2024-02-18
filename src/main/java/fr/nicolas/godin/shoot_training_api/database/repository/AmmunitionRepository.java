package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Ammunition;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface AmmunitionRepository extends CrudRepository<Ammunition, Integer> {
    Ammunition findFirstByOrderByIdDesc();
}