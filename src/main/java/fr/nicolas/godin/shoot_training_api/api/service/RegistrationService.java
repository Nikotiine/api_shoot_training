package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.dto.ValidationCodeDto;
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

    public Boolean emailVerification(String email) throws NullPointerException {

        Shooter shooter = this.shooterRepository.findByEmail(email);
        return this.validationCodeService.emailVerificationAndValidityCode(shooter);

    }

    public Boolean validationCode(ValidationCodeDto code) {
        boolean isActivate = false;
        Shooter shooter = this.shooterRepository.findByEmail(code.getShooterEmail());
        ValidationCode validationCode = this.validationCodeService.getGenerateValidationCode(shooter);
        if (code.getCode() == validationCode.getCode()) {
            // Active le compte
            shooter.setActive(true);
            // Valide le status actif du compte
            this.shooterRepository.save(shooter);
            // Supprime le code en base de données
            this.validationCodeService.deleteActivatedCode(validationCode);
            isActivate = true;
        }
        return isActivate;
    }

    public void refreshValidationCode(RefreshCodeRequest refreshCodeRequest) throws NullPointerException {
        Shooter shooter = this.shooterRepository.findByEmail(refreshCodeRequest.email());
        ValidationCode code = this.validationCodeService.generateValidationCode(shooter);
        this.mailerService.sendValidationCode(code);
    }
}
