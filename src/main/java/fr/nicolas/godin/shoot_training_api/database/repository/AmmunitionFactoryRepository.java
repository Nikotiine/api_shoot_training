package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AmmunitionFactoryRepository extends JpaRepository<AmmunitionFactory,Integer> {
    List<AmmunitionFactory> findAllByActiveIsTrue();
    AmmunitionFactory findFirstByOrderByIdDesc();

    AmmunitionFactory findByName(String name);

}
