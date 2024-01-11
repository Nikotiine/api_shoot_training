package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.database.ActivationCodeType;
import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import fr.nicolas.godin.shoot_training_api.database.repository.ShooterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShooterService {
    private final ShooterRepository shooterRepository;
    private final ActivationCodeService activationCodeService;
    private final MailerService mailerService;

    public void sendCodeForNewPassword(RefreshCodeRequest request) {
        Shooter shooter = this.shooterRepository.findByEmail(request.email());
        ActivationCode code =  this.activationCodeService.generateValidationCode(shooter, ActivationCodeType.RESET_PASSWORD);
        this.mailerService.sendNewPasswordCode(code);
    }
}
