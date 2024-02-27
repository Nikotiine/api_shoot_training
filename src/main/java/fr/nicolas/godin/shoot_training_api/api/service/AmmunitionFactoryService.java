package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionFactoryDto;
import fr.nicolas.godin.shoot_training_api.api.dto.NewAmmunitionFactoryDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionFactory;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionFactoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AmmunitionFactoryService implements AdminInterface<AmmunitionFactoryDto,NewAmmunitionFactoryDto> {

    private AmmunitionFactoryRepository ammunitionFactoryRepository;

    @Override
    public AmmunitionFactoryDto create(NewAmmunitionFactoryDto newFactory) {

        try {
            AmmunitionFactory factory = ModelMapperTool.mapDto(newFactory,AmmunitionFactory.class);
            AmmunitionFactory saved =  this.ammunitionFactoryRepository.save(factory);
            return ModelMapperTool.mapDto(saved,AmmunitionFactoryDto.class);

        } catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.FACTORY_IS_EXIST.getMessage());
        }

    }

    @Override
    public AmmunitionFactoryDto update(AmmunitionFactoryDto updateObjectDto) {
        return null;
    }

    @Override
    public List<AmmunitionFactoryDto> delete(AmmunitionFactoryDto objectDto) {
        AmmunitionFactory factory = this.ammunitionFactoryRepository.findByName(objectDto.getName());
        factory.setActive(false);
        this.ammunitionFactoryRepository.save(factory);
        return this.getAll();
    }

    @Override
    public List<AmmunitionFactoryDto> getAllActive() {
        try {

            List<AmmunitionFactory> factories = this.ammunitionFactoryRepository.findAllByActiveIsTrue();
            return ModelMapperTool.mapList(factories,AmmunitionFactoryDto.class);

        } catch (NullPointerException e){

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }
    }


   @Override
    public long countTotalEntry() {
        return this.ammunitionFactoryRepository.count();
    }

    @Override
    public AmmunitionFactoryDto getLastEntry() {
        AmmunitionFactory factory = this.ammunitionFactoryRepository.findFirstByOrderByIdDesc();
        return ModelMapperTool.mapDto(factory,AmmunitionFactoryDto.class);
    }

  @Override
    public List<AmmunitionFactoryDto> getAll() {
        List<AmmunitionFactory> factories = this.ammunitionFactoryRepository.findAll();
        return ModelMapperTool.mapList(factories,AmmunitionFactoryDto.class);
    }


}
