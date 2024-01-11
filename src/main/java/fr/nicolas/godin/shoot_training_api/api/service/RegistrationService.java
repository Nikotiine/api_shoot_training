package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.dto.ActivationCodeDto;
import fr.nicolas.godin.shoot_training_api.database.ActivationCodeType;
import fr.nicolas.godin.shoot_training_api.database.UserRole;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import fr.nicolas.godin.shoot_training_api.database.repository.ShooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final ShooterRepository shooterRepository;
    private final ActivationCodeService activationCodeService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailerService mailerService;

    public void register(Shooter newShooter){


        Shooter shooter =  this.createNewShooter(newShooter);
        ActivationCode code = this.activationCodeService.generateValidationCode(shooter, ActivationCodeType.ACTIVATION);
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
        return this.activationCodeService.emailVerificationAndValidityCode(shooter);

    }

    public Boolean accountIsAlreadyActivated(String email) throws NullPointerException {
        Shooter shooter = this.shooterRepository.findByEmail(email);
        return shooter.isActive();
    }

    public Boolean validationCode(ActivationCodeDto code) {
        boolean isActivate = false;
        Shooter shooter = this.shooterRepository.findByEmail(code.getEmail());
        ActivationCode activationCode = this.activationCodeService.getGenerateValidationCode(shooter);
        if (code.getCode() == activationCode.getCode() && activationCode.getType() == ActivationCodeType.ACTIVATION) {
            // Active le compte
            shooter.setActive(true);
            // Valide le status actif du compte
            this.shooterRepository.save(shooter);
            // Supprime le code en base de données
            this.activationCodeService.deleteActivatedCode(activationCode);
            isActivate = true;
        }
        return isActivate;
    }

    public void refreshValidationCode(RefreshCodeRequest refreshCodeRequest) throws NullPointerException {
        Shooter shooter = this.shooterRepository.findByEmail(refreshCodeRequest.email());
        boolean codeIsInValidityTime = this.activationCodeService.emailVerificationAndValidityCode(shooter);
        if (!codeIsInValidityTime){
            ActivationCode oldCode = this.activationCodeService.getGenerateValidationCode(shooter);
            this.activationCodeService.deleteActivatedCode(oldCode);
        }
        ActivationCode code = this.activationCodeService.generateValidationCode(shooter,ActivationCodeType.ACTIVATION);
        this.mailerService.sendValidationCode(code);
    }
}
