package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Optics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OpticsRepository extends JpaRepository<Optics,Integer> {

    Optics findFirstByOrderByIdDesc();

    List<Optics> findAllByActiveIsTrue();

    Optics findById(int id);
}
