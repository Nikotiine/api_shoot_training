package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface WeaponRepository extends JpaRepository<Weapon,Integer> {
    Weapon findFirstByOrderByIdDesc();

    List<Weapon> findAllByActiveIsTrue();
}
