package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.UserEditDto;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
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
                throw new BadCredentialsException("Mot de passe invalide");
            }
        }
        return this.userRepository.save(user);
    }
}
