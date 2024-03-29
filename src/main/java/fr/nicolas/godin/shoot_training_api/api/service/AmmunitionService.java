package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.interfaces.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.Ammunition;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AmmunitionService implements AdminInterface<AmmunitionDto, AmmunitionCreateDto> {

    private AmmunitionRepository ammunitionRepository;




    /**
     * Retourne la liste de toutes les munition disponible
     * ADMIN DAO
     * @return List<AmmunitionDto>
     */
    @Override
    public List<AmmunitionDto> getAll() {


        return ModelMapperTool.mapList(this.ammunitionRepository.findAll(), AmmunitionDto.class);
    }

    /**
     * Sauvegarde une nouvelle munition
     *
     * @param newAmmunition NewAmmunitionDto
     * @return la muntion sauvegarde AmmunitionDto
     */
    @Override
    public AmmunitionDto create(AmmunitionCreateDto newAmmunition) {
        try {

            Ammunition ammunition = ModelMapperTool.mapDto(newAmmunition, Ammunition.class);
            Ammunition saved = this.ammunitionRepository.save(ammunition);
            return ModelMapperTool.mapDto(saved, AmmunitionDto.class);

        } catch  (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.AMMUNITION_NAME_AND_FACTORY_EXIST.getMessage());
        }


    }

    @Override
    public AmmunitionDto update(AmmunitionDto ammunitionDto) {
        try {

            Ammunition ammunition = ModelMapperTool.mapDto(ammunitionDto,Ammunition.class);
            Ammunition saved = this.ammunitionRepository.save(ammunition);
            return ModelMapperTool.mapDto(saved,AmmunitionDto.class);

        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.AMMUNITION_NAME_AND_FACTORY_EXIST.getMessage());
        }
    }

    @Override
    public List<AmmunitionDto> delete(int id) {

        try {
            Ammunition ammunition = this.ammunitionRepository.findById(id);
            ammunition.setActive(false);
            this.ammunitionRepository.save(ammunition);
            return this.getAll();
        } catch (NullPointerException e) {

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }

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

        return ModelMapperTool.mapList(this.ammunitionRepository.findAllByActiveIsTrue(), AmmunitionDto.class);

    }
}
