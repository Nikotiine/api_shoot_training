package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.interfaces.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionWeightDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionWeightRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

        try {

            List<AmmunitionWeight> ammunitionWeightList = this.ammunitionWeightRepository.findAmmunitionWeightByCalibersId(id);
            return ModelMapperTool.mapList(ammunitionWeightList, AmmunitionWeightDto.class);
        } catch (NullPointerException e) {

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }


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
     * Retourne la liste complete de AmmunitionWeightDto actif ou non
     *
     * @return List<AmmunitionWeightDto>
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
        try {

            AmmunitionWeight entity = ModelMapperTool.mapDto(ammunitionWeightCreate,AmmunitionWeight.class);
            AmmunitionWeight saved = this.ammunitionWeightRepository.save(entity);
            return ModelMapperTool.mapDto(saved, AmmunitionWeightDto.class);
        } catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.WEIGHT_IS_EXIST.getMessage());
        }

    }

    @Override
    public AmmunitionWeightDto update(AmmunitionWeightDto ammunitionWeightDto) {

        try {
            AmmunitionWeight entity = ModelMapperTool.mapDto(ammunitionWeightDto,AmmunitionWeight.class);
            AmmunitionWeight saved = this.ammunitionWeightRepository.save(entity);
            return ModelMapperTool.mapDto(saved, AmmunitionWeightDto.class);
        } catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.WEIGHT_IS_EXIST.getMessage());
        }

    }

    @Override
    public List<AmmunitionWeightDto> delete(int id) {

        try {

            AmmunitionWeight ammunitionWeight = this.ammunitionWeightRepository.findById(id);
            ammunitionWeight.setActive(false);
            return this.getAll();
        } catch (NullPointerException e){

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }

    }
}
