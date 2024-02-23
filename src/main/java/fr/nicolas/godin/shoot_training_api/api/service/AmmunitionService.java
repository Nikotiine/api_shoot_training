package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminDao;
import fr.nicolas.godin.shoot_training_api.api.dao.UserDao;
import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.Ammunition;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionFactory;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionFactoryRepository;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionRepository;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionWeightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AmmunitionService implements AdminDao<AmmunitionDto>, UserDao<AmmunitionDto> {
    private AmmunitionWeightRepository ammunitionWeightRepository;
    private AmmunitionRepository ammunitionRepository;
    private AmmunitionFactoryRepository ammunitionFactoryRepository;
    private CaliberService caliberService;

    /**
     * Retoune la liste des poids de munition associé au calibre passer en parametre
     * @param id du calibre
     * @return List<AmmunitionWeightDto>
     */
    public List<AmmunitionWeightDto> findAmmunitionWeightByCaliberId(int id) {

        List<AmmunitionWeight> ammunitionWeightList = (List<AmmunitionWeight>) this.ammunitionWeightRepository.findAmmunitionWeightByCalibersId(id);
        return ModelMapperTool.mapList(ammunitionWeightList, AmmunitionWeightDto.class);

    }


    /**
     * Retourne la liste de toutes les munition disponible
     * @return List<AmmunitionDto>
     */
    public List<AmmunitionDto> getAll() {

        List<Ammunition> ammunitionList = (List<Ammunition>) this.ammunitionRepository.findAll();
        return ModelMapperTool.mapList(ammunitionList, AmmunitionDto.class);
    }

    /**
     * Sauvegarde une nouvelle munition
     * @param newAmmunition NewAmmunitionDto
     * @return la muntion sauvegarde AmmunitionDto
     */
    public AmmunitionDto save(NewAmmunitionDto newAmmunition) {

        Ammunition ammunition = ModelMapperTool.mapDto(newAmmunition, Ammunition.class);
        Ammunition saved = this.ammunitionRepository.save(ammunition);
        return ModelMapperTool.mapDto(saved, AmmunitionDto.class);

    }



    /**
     * Retourne la liste necessaire pour l'enregistrement d'une nouvelle munition
     * @return AmmunitionDataCollection
     */
    public AmmunitionDataCollection getDataCollection() {

        List<AmmunitionFactory> ammunitionFactoryList = (List<AmmunitionFactory>) this.ammunitionFactoryRepository.findAll();
        List<AmmunitionFactoryDto> ammunitionFactoryDtoList = ModelMapperTool.mapList(ammunitionFactoryList, AmmunitionFactoryDto.class);
        List<CaliberDto> calibers = this.caliberService.findAllCalibers();

        return new AmmunitionDataCollection(calibers, ammunitionFactoryDtoList);

    }

    @Override
    public AmmunitionDto findLastEntry() {

        Ammunition ammunition = this.ammunitionRepository.findFirstByOrderByIdDesc();
        return ModelMapperTool.mapDto(ammunition, AmmunitionDto.class);
    }

    @Override
    public long countTotalEntry() {

        return this.ammunitionRepository.count();
    }

    public AmmunitionFactoryDto saveNewFactory(NewAmmunitionFactoryDto newAmmunitionFactory) {

        AmmunitionFactory factory = ModelMapperTool.mapDto(newAmmunitionFactory,AmmunitionFactory.class);
        AmmunitionFactory saved = this.ammunitionFactoryRepository.save(factory);
        return ModelMapperTool.mapDto(saved, AmmunitionFactoryDto.class);
    }

    @Override
    public List<AmmunitionDto> getAllActive() {

        List<Ammunition> ammunitionList = (List<Ammunition>) this.ammunitionRepository.findAllByActiveIsTrue();
        return ModelMapperTool.mapList(ammunitionList,AmmunitionDto.class);

    }
}
