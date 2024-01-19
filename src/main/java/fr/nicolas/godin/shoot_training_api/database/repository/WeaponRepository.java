package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Weapon;
import org.springframework.data.repository.CrudRepository;

public interface WeaponRepository extends CrudRepository<Weapon,Integer> {
}
