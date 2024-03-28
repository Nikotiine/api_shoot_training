package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.UserWeaponSetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserWeaponSetupRepository extends JpaRepository<UserWeaponSetup,Integer> {
    List<UserWeaponSetup> findAllByUserId(int id);
}
