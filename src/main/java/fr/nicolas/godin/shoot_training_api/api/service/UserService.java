package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.UserEditDto;
import fr.nicolas.godin.shoot_training_api.api.dto.UserProfileDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Mise a jour du profil utlisateur par l'utilisateur lui meme
     * Met a jour le mot de passe si un nouveau mot de passe est renseigner et si l'ancien mot de passe est le meme qu'en bdd
     * @param userEditDto UserEditDto
     * @return User
     */
    public User update(UserEditDto userEditDto) {

        User user = this.userRepository.findByEmail(userEditDto.getEmail());
        user.setFirstName(userEditDto.getFirstName());
        user.setLastName(userEditDto.getLastName());
        if (userEditDto.getOldPassword() != null) {
            boolean passwordMatch = this.passwordEncoder.matches(userEditDto.getOldPassword(), user.getPassword());
            if (passwordMatch) {

                String hash = this.passwordEncoder.encode(userEditDto.getPassword());
                user.setPassword(hash);
            } else {

                throw new CustomException(CustomExceptionMessage.BAD_PASSWORD.getMessage());
            }
        }
        return this.userRepository.save(user);
    }

    /**
     * Met a jour le role de l'utilisateuer
     * @param user UserProfileDto
     */
    public UserProfileDto updateUserRole(UserProfileDto user) {

        User updateUser = ModelMapperTool.mapDto(user,User.class);
        User saved = this.userRepository.save(updateUser);
       return ModelMapperTool.mapDto(saved,UserProfileDto.class);
    }

    /**
     * Retroune la liste complete de tout les utilisateurs
     * @return UserProfileDto[]
     */
    public List<UserProfileDto> getAll() {

        return ModelMapperTool.mapList(this.userRepository.findAll(), UserProfileDto.class);
    }

    public List<UserProfileDto> disableUser(int id) {
        try {

            User user = this.userRepository.findById(id);
            user.setActive(false);
            this.userRepository.save(user);
            return this.getAll();
        } catch (NullPointerException e) {

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }

    }

    public long countTotalEntry() {

        return this.userRepository.count();
    }

    public UserProfileDto getLastEntry() {

        User user = this.userRepository.findFirstByOrderByIdDesc();
        return ModelMapperTool.mapDto(user, UserProfileDto.class);
    }


}
