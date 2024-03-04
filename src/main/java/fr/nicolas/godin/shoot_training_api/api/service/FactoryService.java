package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.FactoryDto;
import fr.nicolas.godin.shoot_training_api.api.dto.FactoryCreateDto;
import fr.nicolas.godin.shoot_training_api.api.enums.FactoryType;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.Factory;
import fr.nicolas.godin.shoot_training_api.database.repository.FactoryRepository;
import lombok.AllArgsConstructor;
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
        Factory entity = ModelMapperTool.mapDto(newObjectDto,Factory.class);
        Factory saved = this.factoryRepository.save(entity);
        return ModelMapperTool.mapDto(saved, FactoryDto.class);
    }

    @Override
    public FactoryDto update(FactoryDto updateObjectDto) {
        return null;
    }

    @Override
    public List<FactoryDto> delete(FactoryDto deleteObjectDto) {
        return null;
    }
}
