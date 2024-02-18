package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminDao;
import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionDto;
import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightDto;
import fr.nicolas.godin.shoot_training_api.api.dto.NewAmmunitionDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.Ammunition;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionRepository;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionWeightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AmmunitionService implements AdminDao<AmmunitionDto> {
    private AmmunitionWeightRepository ammunitionWeightRepository;
    private AmmunitionRepository ammunitionRepository;

    public List<AmmunitionWeightDto> findAmmunitionWeightByCaliberId(int id) {

        List<AmmunitionWeight> ammunitionWeightList = (List<AmmunitionWeight>) this.ammunitionWeightRepository.findAmmunitionWeightByCalibersId(id);
        return ModelMapperTool.mapList(ammunitionWeightList, AmmunitionWeightDto.class);

    }


    public List<AmmunitionDto> getAll() {

        List<Ammunition> ammunitionList = (List<Ammunition>) this.ammunitionRepository.findAll();
        return ModelMapperTool.mapList(ammunitionList, AmmunitionDto.class);
    }

    public AmmunitionDto save(NewAmmunitionDto newAmmunition) {
        Ammunition ammunition = ModelMapperTool.mapDto(newAmmunition,Ammunition.class);
        Ammunition saved = this.ammunitionRepository.save(ammunition);
        return ModelMapperTool.mapDto(saved, AmmunitionDto.class);
    }

    @Override
    public AmmunitionDto findLastEntry(){
        Ammunition ammunition = this.ammunitionRepository.findFirstByOrderByIdDesc();
        return null;
    }

    @Override
    public long countTotalEntry(){
        return this.ammunitionRepository.count();
    }
}
