package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.interfaces.SimpleInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.OpticsUnitDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.OpticsUnit;
import fr.nicolas.godin.shoot_training_api.database.repository.OpticsUnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpticsUnitService implements SimpleInterface<OpticsUnitDto> {
    private OpticsUnitRepository opticsUnitRepository;

    public List<OpticsUnitDto> getAll(){

        return ModelMapperTool.mapList(this.opticsUnitRepository.findAll(), OpticsUnitDto.class);
    }
}
