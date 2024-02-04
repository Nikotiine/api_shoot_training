package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.UserWeaponSetup;
import org.springframework.data.repository.CrudRepository;

public interface UserWeaponSetupRepository extends CrudRepository<UserWeaponSetup,Integer> {
    Iterable<UserWeaponSetup> findAllByUserId(int id);
}
