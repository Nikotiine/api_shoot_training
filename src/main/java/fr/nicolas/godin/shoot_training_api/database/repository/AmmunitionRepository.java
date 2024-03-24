package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Ammunition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AmmunitionRepository extends JpaRepository<Ammunition, Integer> {
    Ammunition findFirstByOrderByIdDesc();

    List<Ammunition> findAllByActiveIsTrue();

    Ammunition findById(int id);
}