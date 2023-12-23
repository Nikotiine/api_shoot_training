package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.database.UserRole;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import fr.nicolas.godin.shoot_training_api.database.entity.ValidationCode;
import fr.nicolas.godin.shoot_training_api.database.repository.ShooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class RegistrationService {

    private final ShooterRepository shooterRepository;
    private final ValidationCodeService validationCodeService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailerService mailerService;

    public void register(Shooter newShooter){

        Shooter shooter =  this.createNewShooter(newShooter);
        ValidationCode code = this.validationCodeService.generateValidationCode(shooter);
        this.mailerService.sendValidationCode(code);

    }

    private Shooter createNewShooter(Shooter newShooter) {

        String hash = this.passwordEncoder.encode(newShooter.getPassword());
        newShooter.setPassword(hash);
        newShooter.setRole(UserRole.USER);
        newShooter.setActive(false);
        return this.shooterRepository.save(newShooter);

    }


}
