package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.dao.SimpleInterface;
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
        List<OpticsUnit> opticsUnitList = (List<OpticsUnit>) this.opticsUnitRepository.findAll();
        return ModelMapperTool.mapList(opticsUnitList, OpticsUnitDto.class);
    }
}
