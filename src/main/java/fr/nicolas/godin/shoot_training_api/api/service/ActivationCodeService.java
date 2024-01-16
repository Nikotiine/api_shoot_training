package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.database.ActivationCodeType;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import fr.nicolas.godin.shoot_training_api.database.repository.ActivationCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class ActivationCodeService {

    private final ActivationCodeRepository activationCodeRepository;

    /**
     * Genere le code de validation du compte utilisateur qui viens de s'inscire
     * @param user user nouvel utlisateur
     * @return ValidationCode le code de validation pour l'envoie par email
     */
    public ActivationCode generateValidationCode(User user, ActivationCodeType type) {

        ActivationCode code = new ActivationCode();
        Date createdAt =  new Date();
        // Temps de validité du code en milisecondes
        int timeOfValidity = 60 * 1000* 10;
        code.setUser(user);
        code.setTimeOfValidity(new Date(createdAt.getTime()+(timeOfValidity)));
        code.setCode(this.generateRandomNumber());
        code.setType(type);
        return this.activationCodeRepository.save(code);

    }

    /**
     * Verifie que le code associe au compte qui viens d'etre passer en parametre est encore valide
     * @param user user
     * @return boolean
     */
    public boolean emailVerificationAndValidityCode(User user) {

        Date now = new Date();
        ActivationCode code = activationCodeRepository.findByuser(user);
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
     * @param user user
     * @return le code de validation associer au compte utilisateur
     */
    public ActivationCode getGenerateValidationCode(User user) {
        return this.activationCodeRepository.findByuser(user);
    }

    /**
     * Supprime le code de la bdd une fois le compte activé
     * @param activationCode ValidationCodes
     */
    public void deleteActivatedCode(ActivationCode activationCode) {

        this.activationCodeRepository.delete(activationCode);

    }
}
