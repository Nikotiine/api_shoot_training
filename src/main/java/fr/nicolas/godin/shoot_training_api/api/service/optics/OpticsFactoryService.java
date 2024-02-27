package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.NewOpticsDto;
import fr.nicolas.godin.shoot_training_api.api.dto.NewOpticsFactoryDto;
import fr.nicolas.godin.shoot_training_api.api.dto.OpticsFactoryDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.OpticsFactory;
import fr.nicolas.godin.shoot_training_api.database.repository.OpticsFactoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpticsFactoryService implements AdminInterface<OpticsFactoryDto, NewOpticsFactoryDto> {
    private OpticsFactoryRepository opticsFactoryRepository;
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
    public OpticsFactoryDto getLastEntry() {
        return null;
    }

    /**
     * Retourne la liste complete de T actif ou non
     *
     * @return List<T>
     */
    @Override
    public List<OpticsFactoryDto> getAll() {
        return null;
    }

    @Override
    public List<OpticsFactoryDto> getAllActive() {
        return null;
    }

    @Override
    public OpticsFactoryDto create(NewOpticsFactoryDto newOpticsFactory) {
        try {

            OpticsFactory factory = ModelMapperTool.mapDto(newOpticsFactory,OpticsFactory.class);
            OpticsFactory saved = this.opticsFactoryRepository.save(factory);
            return ModelMapperTool.mapDto(saved,OpticsFactoryDto.class);

        } catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.FACTORY_IS_EXIST.getMessage());
        }
    }

    @Override
    public OpticsFactoryDto update(OpticsFactoryDto updateObjectDto) {
        return null;
    }

    @Override
    public List<OpticsFactoryDto> delete(OpticsFactoryDto deleteObjectDto) {
        return null;
    }
}
