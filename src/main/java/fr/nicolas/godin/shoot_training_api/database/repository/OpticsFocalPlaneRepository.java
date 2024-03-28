package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.OpticsFocalPlane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OpticsFocalPlaneRepository  extends JpaRepository<OpticsFocalPlane,Integer> {
}
