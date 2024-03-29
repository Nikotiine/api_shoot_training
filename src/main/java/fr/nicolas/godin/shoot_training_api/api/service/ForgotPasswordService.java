package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.NewPasswordRequestDto;
import fr.nicolas.godin.shoot_training_api.api.dto.RefreshCodeRequest;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.ActivationCodeType;
import fr.nicolas.godin.shoot_training_api.database.entity.ActivationCode;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import fr.nicolas.godin.shoot_training_api.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class ForgotPasswordService {
    private final UserRepository userRepository;
    private final ActivationCodeService activationCodeService;
    private final MailerService mailerService;
    private final BCryptPasswordEncoder passwordEncoder;



    /**
     * Changement du mot de passe et verification du code transmis par email
     * @param newPasswordRequestDto NewPasswordRequestDto , user
     */
    public void changePassword(User user,NewPasswordRequestDto newPasswordRequestDto) {

        ActivationCode code = this.activationCodeService.getGeneratedValidationCode(user);

        if (code.getType() == ActivationCodeType.RESET_PASSWORD && code.getCode() == newPasswordRequestDto.getCode() ){

            String hash = this.passwordEncoder.encode(newPasswordRequestDto.getPassword());
            user.setPassword(hash);
            this.userRepository.save(user);
            this.activationCodeService.deleteActivatedCode(code);


        }else {
            throw new CustomException(CustomExceptionMessage.BAD_ACTIVATION_CODE.getMessage());
        }

    }

    /**
     * Verifie que le code est valide
     * @param newPasswordRequestDto email de l'utilisateur
     */

    public void save(NewPasswordRequestDto newPasswordRequestDto) {

        User user = this.userRepository.findByEmail(newPasswordRequestDto.getEmail());
        if (user == null) {

            throw new CustomException(CustomExceptionMessage.EMAIL_IS_INVALID.getMessage());
        }else {

            this.changePassword(user,newPasswordRequestDto);
        }

    }

    /**
     * Verifie que le code a deja etait envoye
     * Si le code est depasser il est renvoyer automotiquement
     * @param request email de l'utilisateur
     */
    public void requestValidationCodeForNewPassword(RefreshCodeRequest request) {

        Date now = new Date();
        User user = this.userRepository.findByEmailAndActiveIsTrue(request.email());
        if (user == null) {

            throw new CustomException(CustomExceptionMessage.EMAIL_IS_INVALID.getMessage());
        } else {

            ActivationCode code = this.activationCodeService.getGeneratedValidationCode(user);
            if (code == null) {

                this.sendValidationCodeForNewPassword(user);
            } else if (!now.before(code.getTimeOfValidity())) {

                this.activationCodeService.deleteActivatedCode(code);
                this.sendValidationCodeForNewPassword(user);
            } else {

                throw new CustomException(CustomExceptionMessage.CODE_IS_ALREADY_SEND.getMessage());
            }

        }
    }

    /**
     * Envoie le code verficication pour la demande de mot passe oublié
     * @param user RefreshCodeRequest
     */
    public void sendValidationCodeForNewPassword(User user) {

        ActivationCode code =  this.activationCodeService.generateValidationCode(user, ActivationCodeType.RESET_PASSWORD);
        this.mailerService.sendNewPasswordCode(code);
    }

}
