package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Caliber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaliberRepository extends JpaRepository<Caliber,Integer> {
    Caliber findFirstByOrderByIdDesc();

    List<Caliber> findAllByActiveIsTrue();
}
