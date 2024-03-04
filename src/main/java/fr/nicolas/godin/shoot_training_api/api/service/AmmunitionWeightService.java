package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionRepository;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionWeightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AmmunitionWeightService implements AdminInterface<AmmunitionWeightDto, AmmunitionWeightCreateDto> {

    private AmmunitionWeightRepository ammunitionWeightRepository;

    /**
     * Retoune la liste des poids de munition associé au calibre passer en parametre
     *
     * @param id du calibre
     * @return List<AmmunitionWeightDto>
     */
    public List<AmmunitionWeightDto> findAmmunitionWeightByCaliberId(int id) {

        List<AmmunitionWeight> ammunitionWeightList = this.ammunitionWeightRepository.findAmmunitionWeightByCalibersId(id);
        return ModelMapperTool.mapList(ammunitionWeightList, AmmunitionWeightDto.class);

    }

    /**
     * Compte le nombre d'entree total en base de donnée
     *
     * @return long
     */
    @Override
    public long countTotalEntry() {
        return 0;
    }

    /**
     * Retourne la derniere entree de la base de donnée
     *
     * @return T
     */
    @Override
    public AmmunitionWeightDto getLastEntry() {
        return null;
    }

    /**
     * Retourne la liste complete de T actif ou non
     *
     * @return List<T>
     */
    @Override
    public List<AmmunitionWeightDto> getAll() {
        List<AmmunitionWeight> ammunitionWeightList = this.ammunitionWeightRepository.findAll();
        return ModelMapperTool.mapList(ammunitionWeightList, AmmunitionWeightDto.class);
    }

    @Override
    public List<AmmunitionWeightDto> getAllActive() {
        return null;
    }

    @Override
    public AmmunitionWeightDto create(AmmunitionWeightCreateDto ammunitionWeightCreate) {
        AmmunitionWeight entity = ModelMapperTool.mapDto(ammunitionWeightCreate,AmmunitionWeight.class);
        AmmunitionWeight saved = this.ammunitionWeightRepository.save(entity);

        return ModelMapperTool.mapDto(saved, AmmunitionWeightDto.class);
    }

    @Override
    public AmmunitionWeightDto update(AmmunitionWeightDto updateObjectDto) {
        return null;
    }

    @Override
    public List<AmmunitionWeightDto> delete(AmmunitionWeightDto deleteObjectDto) {
        return null;
    }
}
