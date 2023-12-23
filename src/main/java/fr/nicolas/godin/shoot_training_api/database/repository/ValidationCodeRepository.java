package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.ValidationCode;
import org.springframework.data.repository.CrudRepository;

public interface ValidationCodeRepository extends CrudRepository<ValidationCode,Integer> {
}
