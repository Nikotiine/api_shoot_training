package fr.nicolas.godin.shoot_training_api.api.service.weapon;

import fr.nicolas.godin.shoot_training_api.api.interfaces.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.*;
import fr.nicolas.godin.shoot_training_api.database.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class WeaponService implements AdminInterface<WeaponDto, WeaponCreateDto> {

    private final WeaponRepository weaponRepository;


    public WeaponDto create(WeaponCreateDto weaponDto) {
        try {

            Weapon weapon = ModelMapperTool.mapDto(weaponDto, Weapon.class);
            Weapon saved = this.weaponRepository.save(weapon);
            return ModelMapperTool.mapDto(saved,WeaponDto.class);
        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.WEAPON_MODEL_IS_EXIST.getMessage());
        }
    }

    @Override
    public WeaponDto update(WeaponDto weaponDto) {
        try {

            Weapon weapon = ModelMapperTool.mapDto(weaponDto, Weapon.class);
            Weapon saved = this.weaponRepository.save(weapon);
            return ModelMapperTool.mapDto(saved, WeaponDto.class);
        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.WEAPON_MODEL_IS_EXIST.getMessage());
        }
    }

    @Override
    public List<WeaponDto> delete(int id) {
        try {

            Weapon weapon = this.weaponRepository.findById(id);
            weapon.setActive(false);
            this.weaponRepository.save(weapon);
            return this.getAll();
        } catch (NullPointerException e) {

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }

    }


    @Override
    public List<WeaponDto> getAll() {

        return ModelMapperTool.mapList(this.weaponRepository.findAll(),WeaponDto.class);
    }


    @Override
    public long countTotalEntry() {

        return this.weaponRepository.count();
    }

    @Override
    public WeaponDto getLastEntry() {

        Weapon weapon = this.weaponRepository.findFirstByOrderByIdDesc();
        return ModelMapperTool.mapDto(weapon, WeaponDto.class);
    }



    @Override
    public List<WeaponDto> getAllActive() {

        return ModelMapperTool.mapList(this.weaponRepository.findAllByActiveIsTrue(),WeaponDto.class);
    }
}
