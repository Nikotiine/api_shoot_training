package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.UserWeaponSetupCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.UserWeaponSetupDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.UserWeaponSetup;
import fr.nicolas.godin.shoot_training_api.database.repository.UserWeaponSetupRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserSetupService {


    private UserWeaponSetupRepository weaponSetupRepository;
    public UserWeaponSetupDto create(UserWeaponSetupCreateDto userWeaponSetupCreateDto) {

        try {

            UserWeaponSetup userWeaponSetup = ModelMapperTool.mapDto(userWeaponSetupCreateDto,UserWeaponSetup.class);
            UserWeaponSetup saved =  this.weaponSetupRepository.save(userWeaponSetup);
            return ModelMapperTool.mapDto(saved, UserWeaponSetupDto.class);
        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.WEAPON_SETUP_IS_EXIST.getMessage());
        }
    }

    public List<UserWeaponSetupDto> getAllActive(int userId) {

        return ModelMapperTool.mapList(this.weaponSetupRepository.findAllByUserId(userId), UserWeaponSetupDto.class);
    }
}
