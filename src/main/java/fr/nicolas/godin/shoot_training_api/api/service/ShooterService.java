package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.ShooterEditDto;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import fr.nicolas.godin.shoot_training_api.database.repository.ShooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShooterService {
    private ShooterRepository shooterRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Shooter update(ShooterEditDto shooterEditDto) {
        Shooter shooter = this.shooterRepository.findByEmail(shooterEditDto.getEmail());
        shooter.setFirstName(shooterEditDto.getFirstName());
        shooter.setLastName(shooterEditDto.getLastName());
        if (shooterEditDto.getOldPassword() != null){
            boolean passwordMatch = this.passwordEncoder.matches(shooterEditDto.getOldPassword(),shooter.getPassword());
            if (passwordMatch){
                String hash = this.passwordEncoder.encode(shooterEditDto.getPassword());
                shooter.setPassword(hash);
            }else {
                throw new BadCredentialsException("Mot de passe invalide");
            }
        }
        return this.shooterRepository.save(shooter);
    }
}
