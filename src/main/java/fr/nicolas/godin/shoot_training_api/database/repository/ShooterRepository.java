package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShooterRepository extends CrudRepository<Shooter,Integer> {

  Shooter findByEmailAndActiveIsTrue(String email);
  Shooter findByEmail(String email);
}
