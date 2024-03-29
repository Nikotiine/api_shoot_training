package fr.nicolas.godin.shoot_training_api.api.service.weapon;

import fr.nicolas.godin.shoot_training_api.api.interfaces.SimpleInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.WeaponTypeDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.WeaponType;
import fr.nicolas.godin.shoot_training_api.database.repository.WeaponTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WeaponTypeService implements SimpleInterface<WeaponTypeDto> {
    private final WeaponTypeRepository weaponTypeRepository;

    @Override
    public List<WeaponTypeDto> getAll() {

        return ModelMapperTool.mapList(this.weaponTypeRepository.findAll(), WeaponTypeDto.class);
    }
}
