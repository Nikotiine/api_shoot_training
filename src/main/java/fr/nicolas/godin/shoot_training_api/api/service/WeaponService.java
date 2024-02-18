package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminDao;
import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.*;
import fr.nicolas.godin.shoot_training_api.database.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class WeaponService implements AdminDao<WeaponDto> {

    private final WeaponRepository weaponRepository;
    private final WeaponFactoryRepository weaponFactoryRepository;
    private final WeaponCategoryRepository weaponCategoryRepository;
    private final CaliberRepository  caliberRepository;
    private final WeaponTypeRepository weaponTypeRepository;





    public WeaponDto save(NewWeaponDto weaponDto) {
        try {

            Weapon weapon = ModelMapperTool.mapDto(weaponDto, Weapon.class);
            Weapon saved = this.weaponRepository.save(weapon);
            return ModelMapperTool.mapDto(saved,WeaponDto.class);

        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.WEAPON_MODEL_IS_EXIST.getMessage());

        }
    }

    public WeaponDataCollection getDataCollection() {

        List<WeaponFactory>  weaponFactories = (List<WeaponFactory>) this.weaponFactoryRepository.findAll();
        List<WeaponFactoryDto>  weaponFactoryDtoList = ModelMapperTool.mapList(weaponFactories, WeaponFactoryDto.class);

        List<Caliber> calibers = (List<Caliber>) this.caliberRepository.findAll();
        List<CaliberDto> caliberDtoList = ModelMapperTool.mapList(calibers, CaliberDto.class);

        List<WeaponCategory> weaponCategories = (List<WeaponCategory>) this.weaponCategoryRepository.findAll();
        List<WeaponCategoryDto> weaponCategoryDtoList = ModelMapperTool.mapList(weaponCategories, WeaponCategoryDto.class);

        List<WeaponType> weaponTypes = (List<WeaponType>) this.weaponTypeRepository.findAll();
        List<WeaponTypeDto> weaponTypeDtoList = ModelMapperTool.mapList(weaponTypes, WeaponTypeDto.class);

        return new WeaponDataCollection(weaponFactoryDtoList,weaponTypeDtoList,weaponCategoryDtoList,caliberDtoList);
    }


    public List<WeaponDto> getAll() {
        List<Weapon> weapons = (List<Weapon>) this.weaponRepository.findAll();
        return ModelMapperTool.mapList(weapons,WeaponDto.class);
    }


    @Override
    public long countTotalEntry() {
        return this.weaponRepository.count();
    }

    @Override
    public WeaponDto findLastEntry() {
        Weapon weapon = this.weaponRepository.findFirstByOrderByIdDesc();
        return ModelMapperTool.mapDto(weapon, WeaponDto.class);
    }
}
