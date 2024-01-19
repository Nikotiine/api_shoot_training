package fr.nicolas.godin.shoot_training_api.database.repository;

import fr.nicolas.godin.shoot_training_api.database.entity.WeaponCategory;
import org.springframework.data.repository.CrudRepository;

public interface WeaponCategoryRepository extends CrudRepository<WeaponCategory,Integer> {
}
