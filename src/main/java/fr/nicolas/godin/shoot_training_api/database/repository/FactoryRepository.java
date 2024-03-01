package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.api.enums.FactoryType;
import fr.nicolas.godin.shoot_training_api.database.entity.Factory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactoryRepository extends JpaRepository<Factory,Integer> {

    List<Factory> findAllByType(FactoryType type);
    List<Factory> findAllByActiveIsTrue();
}
