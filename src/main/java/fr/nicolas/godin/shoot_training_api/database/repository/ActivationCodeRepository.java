package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import org.springframework.data.repository.CrudRepository;

public interface ActivationCodeRepository extends CrudRepository<ActivationCode,Integer> {
    ActivationCode findByuser(User user);
}
