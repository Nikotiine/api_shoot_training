package fr.nicolas.godin.shoot_training_api.api.service.weapon;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.NewWeaponFactoryDto;
import fr.nicolas.godin.shoot_training_api.api.dto.WeaponFactoryDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.WeaponFactory;
import fr.nicolas.godin.shoot_training_api.database.repository.WeaponFactoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WeaponFactoryService implements AdminInterface<WeaponFactoryDto, NewWeaponFactoryDto> {

    private final WeaponFactoryRepository weaponFactoryRepository;
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
    public WeaponFactoryDto getLastEntry() {
        return null;
    }

    /**
     * Retourne la liste complete de T actif ou non
     *
     * @return List<T>
     */
    @Override
    public List<WeaponFactoryDto> getAll() {
        return null;
    }

    @Override
    public List<WeaponFactoryDto> getAllActive() {
        List<WeaponFactory>  weaponFactories = (List<WeaponFactory>) this.weaponFactoryRepository.findAll();
        return ModelMapperTool.mapList(weaponFactories,WeaponFactoryDto.class);
    }

    @Override
    public WeaponFactoryDto create(NewWeaponFactoryDto newWeaponFactory) {
        try {

            WeaponFactory factory = ModelMapperTool.mapDto(newWeaponFactory,WeaponFactory.class);
            WeaponFactory saved = this.weaponFactoryRepository.save(factory);
            return ModelMapperTool.mapDto(saved,WeaponFactoryDto.class);
        } catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.FACTORY_IS_EXIST.getMessage());
        }
    }

    @Override
    public WeaponFactoryDto update(WeaponFactoryDto updateObjectDto) {
        return null;
    }

    @Override
    public List<WeaponFactoryDto> delete(WeaponFactoryDto deleteObjectDto) {
        return null;
    }
}
