package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import fr.nicolas.godin.shoot_training_api.database.entity.ValidationCode;
import fr.nicolas.godin.shoot_training_api.database.repository.ValidationCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class ValidationCodeService {

    private final ValidationCodeRepository validationCodeRepository;

    public ValidationCode generateValidationCode(Shooter shooter) {

        ValidationCode code = new ValidationCode();
        Date createdAt =  shooter.getCreatedAT();
        code.setShooter(shooter);
        code.setTimeOfValidity(new Date(createdAt.getTime()+(60 * 1000* 10)));
        code.setCode(this.generateRandomNumber());
        return this.validationCodeRepository.save(code);

    }

    /**
     * Genere unn code aleatoire a 6 chiffres
     * @return int Code aleatoire
     */
    private int generateRandomNumber() {

        Random random = new Random();
        return 100000 + random.nextInt(900000);

    }
}
