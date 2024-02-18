package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface UserRepository extends CrudRepository<User,Integer> {

  User findByEmailAndActiveIsTrue(String email);
  User findByEmail(String email);

  User findFirstByOrderByIdDesc();
}
