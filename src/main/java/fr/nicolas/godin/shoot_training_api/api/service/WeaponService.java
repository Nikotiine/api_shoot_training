package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.*;
import fr.nicolas.godin.shoot_training_api.database.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class WeaponService {

    private final WeaponRepository weaponRepository;
    private final WeaponFactoryRepository weaponFactoryRepository;
    private final WeaponCategoryRepository weaponCategoryRepository;
    private final CaliberRepository  caliberRepository;
    private final WeaponTypeRepository weaponTypeRepository;

    private ModelMapper modelMapper;




    public Weapon save(Weapon weapon) {
        try {

           return this.weaponRepository.save(weapon);

        } catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.WEAPON_MODEL_IS_EXIST.getMessage());

        }
    }

    public WeaponDataCollection getDataCollection() {

        List<WeaponFactory>  weaponFactories = (List<WeaponFactory>) this.weaponFactoryRepository.findAll();
        List<WeaponFactoryDto>  weaponFactoryDtoList =
                weaponFactories
                        .stream()
                        .map(weaponFactory -> this.modelMapper.map(weaponFactory, WeaponFactoryDto.class))
                        .toList();

        List<Caliber> calibers = (List<Caliber>) this.caliberRepository.findAll();
        List<CaliberDto> caliberDtoList =
                calibers
                        .stream()
                        .map(caliber -> this.modelMapper.map(caliber, CaliberDto.class))
                        .toList();
        List<WeaponCategory> weaponCategories = (List<WeaponCategory>) this.weaponCategoryRepository.findAll();
        List<WeaponCategoryDto> weaponCategoryDtoList =
                weaponCategories
                        .stream()
                        .map(weaponCategory -> this.modelMapper.map(weaponCategory, WeaponCategoryDto.class))
                        .toList();
        List<WeaponType> weaponTypes = (List<WeaponType>) this.weaponTypeRepository.findAll();
        List<WeaponTypeDto> weaponTypeDtoList =
                weaponTypes
                        .stream()
                        .map(weaponType -> this.modelMapper.map(weaponType, WeaponTypeDto.class))
                        .toList();
        return new WeaponDataCollection(weaponFactoryDtoList,weaponTypeDtoList,weaponCategoryDtoList,caliberDtoList);
    }


}
