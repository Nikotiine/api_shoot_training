package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import fr.nicolas.godin.shoot_training_api.database.repository.ShooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AuthenticationService implements UserDetailsService {

    private final ShooterRepository shooterRepository;


    @Override
    public Shooter loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.shooterRepository.findByEmailAndActiveIsTrue(username);

    }
}
