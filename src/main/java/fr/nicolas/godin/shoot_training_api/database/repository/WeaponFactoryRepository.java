package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.WeaponFactory;
import org.springframework.data.repository.CrudRepository;

public interface WeaponFactoryRepository extends CrudRepository<WeaponFactory,Integer> {
}
