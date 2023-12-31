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

    /**
     * Genere le code de validation du compte utilisateur qui viens de s'inscire
     * @param shooter Shooter nouvel utlisateur
     * @return ValidationCode le code de validation pour l'envoie par email
     */
    public ValidationCode generateValidationCode(Shooter shooter) {

        ValidationCode code = new ValidationCode();
        Date createdAt =  new Date();
        // Temps de validité du code en milisecondes
        int timeOfValidity = 60 * 1000* 10;
        code.setShooter(shooter);
        code.setTimeOfValidity(new Date(createdAt.getTime()+(timeOfValidity)));
        code.setCode(this.generateRandomNumber());
        return this.validationCodeRepository.save(code);

    }

    /**
     * Verifie que le code associe au compte qui viens d'etre passer en parametre est encore valide
     * @param shooter Shooter
     * @return boolean
     */
    public boolean emailVerificationAndValidityCode(Shooter shooter) {

        Date now = new Date();
        ValidationCode code = validationCodeRepository.findByShooter(shooter);
       // boolean isCodeValidityTime = now.before(code.getTimeOfValidity());
        return now.before(code.getTimeOfValidity());

    }

    /**
     * Genere unn code aleatoire a 6 chiffres
     * @return int Code aleatoire
     */
    private int generateRandomNumber() {

        Random random = new Random();
        return 100000 + random.nextInt(900000);

    }

    /**
     *
     * @param shooter Shooter
     * @return le code de validation associer au compte utilisateur
     */
    public ValidationCode getGenerateValidationCode(Shooter shooter) {
        return this.validationCodeRepository.findByShooter(shooter);
    }

    /**
     * Supprime le code de la bdd une fois le compte activé
     * @param validationCode ValidationCodes
     */
    public void deleteActivatedCode(ValidationCode validationCode) {

        this.validationCodeRepository.delete(validationCode);

    }
}
