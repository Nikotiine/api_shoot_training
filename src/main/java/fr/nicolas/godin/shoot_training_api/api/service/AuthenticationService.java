package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByEmailAndActiveIsTrue(username);

    }
}
