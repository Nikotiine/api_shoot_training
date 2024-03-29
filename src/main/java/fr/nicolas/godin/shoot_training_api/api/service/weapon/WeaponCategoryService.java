package fr.nicolas.godin.shoot_training_api.api.service.weapon;

import fr.nicolas.godin.shoot_training_api.api.interfaces.SimpleInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.WeaponCategoryDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.WeaponCategory;
import fr.nicolas.godin.shoot_training_api.database.repository.WeaponCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WeaponCategoryService implements SimpleInterface<WeaponCategoryDto> {
    private final WeaponCategoryRepository weaponCategoryRepository;

    @Override
    public List<WeaponCategoryDto> getAll() {

        return ModelMapperTool.mapList(this.weaponCategoryRepository.findAll(),WeaponCategoryDto.class);
    }
}
