package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminDao;
import fr.nicolas.godin.shoot_training_api.api.dto.UserEditDto;
import fr.nicolas.godin.shoot_training_api.api.dto.UserProfileDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements AdminDao<UserProfileDto> {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User update(UserEditDto userEditDto) {
        User user = this.userRepository.findByEmail(userEditDto.getEmail());
        user.setFirstName(userEditDto.getFirstName());
        user.setLastName(userEditDto.getLastName());
        if (userEditDto.getOldPassword() != null){
            boolean passwordMatch = this.passwordEncoder.matches(userEditDto.getOldPassword(), user.getPassword());
            if (passwordMatch){
                String hash = this.passwordEncoder.encode(userEditDto.getPassword());
                user.setPassword(hash);
            }else {
                throw new CustomException(CustomExceptionMessage.BAD_PASSWORD.getMessage());
            }
        }
        return this.userRepository.save(user);
    }

    public List<UserProfileDto> getAll() {
        List<User> userList = (List<User>) this.userRepository.findAll();
        return ModelMapperTool.mapList(userList, UserProfileDto.class);
    }

    public long countTotalEntry() {
        return this.userRepository.count();
    }

    @Override
    public UserProfileDto findLastEntry() {
        User user = this.userRepository.findFirstByOrderByIdDesc();
        System.out.println(user);
        return ModelMapperTool.mapDto(user, UserProfileDto.class);
    }


}
