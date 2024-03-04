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

    private ModelMapper modelMapper;
    private UserWeaponSetupRepository weaponSetupRepository;
    public UserWeaponSetupDto create(UserWeaponSetupCreateDto userWeaponSetupCreateDto) {
        UserWeaponSetup userWeaponSetup = this.modelMapper.map(userWeaponSetupCreateDto,UserWeaponSetup.class);

        try {
            UserWeaponSetup saved =  this.weaponSetupRepository.save(userWeaponSetup);
            return this.modelMapper.map(saved, UserWeaponSetupDto.class);

        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.WEAPON_SETUP_IS_EXIST.getMessage());
        }
    }

    public List<UserWeaponSetupDto> getAllActive(int userId) {
        List<UserWeaponSetup> userWeaponSetupList = (List<UserWeaponSetup>) this.weaponSetupRepository.findAllByUserId(userId);
        return ModelMapperTool.mapList(userWeaponSetupList, UserWeaponSetupDto.class);
    }
}
