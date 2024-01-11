package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.NewPasswordRequestDto;
import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.database.ActivationCodeType;
import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import fr.nicolas.godin.shoot_training_api.database.repository.ShooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ShooterService {
    private final ShooterRepository shooterRepository;
    private final ActivationCodeService activationCodeService;
    private final MailerService mailerService;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Envoie le code verficication pour la demande de mot passe oublié
     * @param request RefreshCodeRequest
     */
    public void sendCodeForNewPassword(RefreshCodeRequest request) {

        Shooter shooter = this.shooterRepository.findByEmailAndActiveIsTrue(request.email());
        ActivationCode code =  this.activationCodeService.generateValidationCode(shooter, ActivationCodeType.RESET_PASSWORD);
        this.mailerService.sendNewPasswordCode(code);

    }

    /**
     * Changement du mot de passe et verification du code transmis par email
     * @param newPasswordRequestDto NewPasswordRequestDto
     * @return boolean
     */
    public boolean changePassword(NewPasswordRequestDto newPasswordRequestDto) {

        boolean isPasswordChange= false;
        Shooter shooter = this.shooterRepository.findByEmail(newPasswordRequestDto.getEmail());
        ActivationCode code = this.activationCodeService.getGenerateValidationCode(shooter);

        if (code.getType() == ActivationCodeType.RESET_PASSWORD && code.getCode() == newPasswordRequestDto.getCode() ){

            String hash = this.passwordEncoder.encode(newPasswordRequestDto.getPassword());
            shooter.setPassword(hash);
            this.shooterRepository.save(shooter);
            this.activationCodeService.deleteActivatedCode(code);
            isPasswordChange = true;

        }

        return isPasswordChange;
    }

    /**
     * Verifie que le code est valide
     * @param email email de l'utilisateur
     * @return boolean
     */
    public boolean emailVerificationAndValidityCode(String email) {
        Shooter shooter = this.shooterRepository.findByEmail(email);
        return this.activationCodeService.emailVerificationAndValidityCode(shooter);
    }

    /**
     * Verifie que le code a deja etait envoye
     * Si le code est depasser il est renvoyer automotiquement
     * @param email email de l'utilisateur
     * @return boolean
     */
    public boolean codeIsAlreadySend(String email) {
        boolean isAlreadySend = true;
        Date now = new Date();
        Shooter shooter = this.shooterRepository.findByEmail(email);
        ActivationCode code = this.activationCodeService.getGenerateValidationCode(shooter);
        if (code == null){
            isAlreadySend = false;
        }
        if (code != null && !now.before(code.getTimeOfValidity())){
            this.activationCodeService.deleteActivatedCode(code);
            isAlreadySend = false;
        }
        return isAlreadySend;
    }
}
