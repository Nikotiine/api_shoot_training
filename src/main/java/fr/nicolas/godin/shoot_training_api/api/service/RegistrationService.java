package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.dto.ActivationCodeDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.ActivationCodeType;
import fr.nicolas.godin.shoot_training_api.database.UserRole;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import fr.nicolas.godin.shoot_training_api.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final ActivationCodeService activationCodeService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailerService mailerService;

    /**
     * Enregistre un nouvel utilisateur en bdd et envoie le code d'activation du compte
     * @param newUser user
     */
    public void register(User newUser){

        User user =  this.createNewUser(newUser);
        ActivationCode code = this.activationCodeService.generateValidationCode(user, ActivationCodeType.ACTIVATION);
        this.mailerService.sendValidationCode(code);

    }

    /**
     * Creer l utilisateur en bdd
     * @param newUser user

     */
    private User createNewUser(User newUser) {

        String hash = this.passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hash);
        newUser.setRole(UserRole.USER);
        newUser.setActive(false);
        try {
           return this.userRepository.save(newUser);
        }catch (DataIntegrityViolationException e){
            throw new CustomException(CustomExceptionMessage.EMAIL_IS_ALREADY_USE.getMessage());
        }


    }




    /**
     * Valide le compte de l'utilisateur apres verification du code
     * @param code le code envoye par mail
     */
    public void validationCode(ActivationCodeDto code) {
        Date now = new Date();
        User user = this.userRepository.findByEmail(code.getEmail());
        if (user==null){
            throw new CustomException(CustomExceptionMessage.EMAIL_IS_INVALID.getMessage());
        }else if (user.isActive()){
            throw new CustomException(CustomExceptionMessage.ACCOUNT_IS_ALREADY_ACTIVE.getMessage());
        }else {
            ActivationCode activationCode = this.activationCodeService.getGeneratedValidationCode(user);
            if (!now.before(activationCode.getTimeOfValidity())){
                throw new CustomException(CustomExceptionMessage.BAD_ACTIVATION_CODE.getMessage());
            }else {
                if (code.getCode() == activationCode.getCode() && activationCode.getType() == ActivationCodeType.ACTIVATION) {
                    // Active le compte
                    user.setActive(true);
                    // Valide le status actif du compte
                    this.userRepository.save(user);
                    // Supprime le code en base de données
                    this.activationCodeService.deleteActivatedCode(activationCode);


                }
            }
        }


    }

    /**
     * Renvoie un code a la demande de l'utlisateur si il a depasser le temps impati avant d'activer son compte
     * @param refreshCodeRequest RefreshCodeRequest
     */
    public void refreshValidationCode(RefreshCodeRequest refreshCodeRequest)  {

        User user = this.userRepository.findByEmail(refreshCodeRequest.email());
        if (user==null){

            throw new CustomException(CustomExceptionMessage.EMAIL_IS_ALREADY_USE.getMessage());

        }
        else {

            boolean codeIsInValidityTime = this.activationCodeService.emailVerificationAndValidityCode(user);

            if (!codeIsInValidityTime){

                ActivationCode oldCode = this.activationCodeService.getGeneratedValidationCode(user);
                this.activationCodeService.deleteActivatedCode(oldCode);
                ActivationCode code = this.activationCodeService.generateValidationCode(user,ActivationCodeType.ACTIVATION);
                this.mailerService.sendValidationCode(code);
            }else {

                throw new CustomException(CustomExceptionMessage.CODE_IS_ACTIVE.getMessage());

            }


        }
    }

}
