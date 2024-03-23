package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.FactoryDto;
import fr.nicolas.godin.shoot_training_api.api.dto.FactoryCreateDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.enums.FactoryType;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.Factory;
import fr.nicolas.godin.shoot_training_api.database.repository.FactoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FactoryService implements AdminInterface<FactoryDto, FactoryCreateDto> {

    private FactoryRepository factoryRepository;

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
    public FactoryDto getLastEntry() {
        return null;
    }

    /**
     * Retourne la liste complete de T actif ou non
     *
     * @return List<T>
     */
    @Override
    public List<FactoryDto> getAll() {
        return null;
    }

    @Override
    public List<FactoryDto> getAllActive() {
        return null;
    }

    public List<FactoryDto> getAllByType(FactoryType type) {
        return ModelMapperTool.mapList(this.factoryRepository.findAllByType(type), FactoryDto.class);
    }

    @Override
    public FactoryDto create(FactoryCreateDto newObjectDto) {
        try {

            Factory entity = ModelMapperTool.mapDto(newObjectDto,Factory.class);
            Factory saved = this.factoryRepository.save(entity);
            return ModelMapperTool.mapDto(saved, FactoryDto.class);

        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.FACTORY_IS_EXIST.getMessage());
        }

    }

    @Override
    public FactoryDto update(FactoryDto factoryDto) {
        try {
            Factory factory = ModelMapperTool.mapDto(factoryDto,Factory.class);
            Factory saved = this.factoryRepository.save(factory);
            return ModelMapperTool.mapDto(saved,FactoryDto.class);

        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.FACTORY_IS_EXIST.getMessage());
        }

    }

    @Override
    public List<FactoryDto> delete(int id) {

        Factory factory = this.factoryRepository.findById(id);
        factory.setActive(false);
        this.factoryRepository.save(factory);
        return this.getAllByType(factory.getType());
    }
}
