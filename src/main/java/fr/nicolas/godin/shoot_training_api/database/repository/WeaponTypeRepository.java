package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.WeaponType;
import org.springframework.data.repository.CrudRepository;

public interface WeaponTypeRepository extends CrudRepository<WeaponType,Integer> {
}
