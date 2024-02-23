package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Optics;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface OpticsRepository extends CrudRepository<Optics,Integer> {

    Optics findFirstByOrderByIdDesc();

    Iterable<Optics> findAllByActiveIsTrue();
}
