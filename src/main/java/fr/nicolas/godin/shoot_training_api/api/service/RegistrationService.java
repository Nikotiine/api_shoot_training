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

    /**
     * Enregistre un nouvel utilisateur en bdd et envoie le code d'activation du compte
     * @param newShooter Shooter
     */
    public void register(Shooter newShooter){

        Shooter shooter =  this.createNewShooter(newShooter);
        ActivationCode code = this.activationCodeService.generateValidationCode(shooter, ActivationCodeType.ACTIVATION);
        this.mailerService.sendValidationCode(code);

    }

    /**
     * Creer l utilisateur en bdd
     * @param newShooter Shooter
     * @return Shooter
     */
    private Shooter createNewShooter(Shooter newShooter) {

        String hash = this.passwordEncoder.encode(newShooter.getPassword());
        newShooter.setPassword(hash);
        newShooter.setRole(UserRole.USER);
        newShooter.setActive(false);
        return this.shooterRepository.save(newShooter);

    }

    /**
     * Verifie que le mail existe bien en bdd
     * @param email mail
     * @return Boolean
     * @throws NullPointerException si le mail fournis n'existe pas en bdd
     */
    public Boolean emailVerification(String email) throws NullPointerException {

        Shooter shooter = this.shooterRepository.findByEmail(email);
        return this.activationCodeService.emailVerificationAndValidityCode(shooter);

    }

    /**
     * Verifie que le compte est deja actif
     * @param email email
     * @return Boolean
     * @throws NullPointerException si le mail fournis n'existe pas en bdd
     */
    public Boolean accountIsAlreadyActivated(String email) throws NullPointerException {

        Shooter shooter = this.shooterRepository.findByEmail(email);
        return shooter.isActive();

    }

    /**
     * Valide le compte de l'utilisateur apres verification du code
     * @param code le code envoye par mail
     * @return Boolean
     */
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

    /**
     * Renvoie un code a la demande de l'utlisateur si il a depasser le temps impati avant d'activer son compte
     * @param refreshCodeRequest RefreshCodeRequest
     * @throws NullPointerException si le mail fournis n'existe pas en bdd
     */
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
