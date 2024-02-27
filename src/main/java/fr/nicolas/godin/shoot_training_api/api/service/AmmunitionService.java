package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dao.CommonInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.Ammunition;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AmmunitionService implements AdminInterface<AmmunitionDto,NewAmmunitionDto> {

    private AmmunitionRepository ammunitionRepository;




    /**
     * Retourne la liste de toutes les munition disponible
     * ADMIN DAO
     * @return List<AmmunitionDto>
     */
    @Override
    public List<AmmunitionDto> getAll() {

        List<Ammunition> ammunitionList = (List<Ammunition>) this.ammunitionRepository.findAll();
        return ModelMapperTool.mapList(ammunitionList, AmmunitionDto.class);
    }

    /**
     * Sauvegarde une nouvelle munition
     *
     * @param newAmmunition NewAmmunitionDto
     * @return la muntion sauvegarde AmmunitionDto
     */
    @Override
    public AmmunitionDto create(NewAmmunitionDto newAmmunition) {

        Ammunition ammunition = ModelMapperTool.mapDto(newAmmunition, Ammunition.class);
        Ammunition saved = this.ammunitionRepository.save(ammunition);
        return ModelMapperTool.mapDto(saved, AmmunitionDto.class);

    }

    @Override
    public AmmunitionDto update(AmmunitionDto updateObjectDto) {
        return null;
    }

    @Override
    public List<AmmunitionDto> delete(AmmunitionDto objectDto) {
        return null;
    }



    @Override
    public AmmunitionDto getLastEntry() {

        Ammunition ammunition = this.ammunitionRepository.findFirstByOrderByIdDesc();
        return ModelMapperTool.mapDto(ammunition, AmmunitionDto.class);
    }

    @Override
    public long countTotalEntry() {

        return this.ammunitionRepository.count();
    }


    @Override
    public List<AmmunitionDto> getAllActive() {

        List<Ammunition> ammunitionList = (List<Ammunition>) this.ammunitionRepository.findAllByActiveIsTrue();
        return ModelMapperTool.mapList(ammunitionList, AmmunitionDto.class);

    }
}
