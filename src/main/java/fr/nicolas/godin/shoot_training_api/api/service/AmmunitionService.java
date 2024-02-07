package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionWeightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AmmunitionService {
    private AmmunitionWeightRepository ammunitionWeightRepository;

    public List<AmmunitionWeightDto> findAmmunitionWeightByCaliberId(int id){
        List<AmmunitionWeight> ammunitionWeightList = (List<AmmunitionWeight>) this.ammunitionWeightRepository.findAmmunitionWeightByCalibersId(id);
        return ModelMapperTool.mapList(ammunitionWeightList, AmmunitionWeightDto.class);
    }
}
