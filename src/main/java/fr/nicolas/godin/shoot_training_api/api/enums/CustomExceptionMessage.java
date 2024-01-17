package fr.nicolas.godin.shoot_training_api.api.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum CustomExceptionMessage {
    EMAIL_IS_ALREADY_USE(HttpStatus.BAD_REQUEST,"Cet email est deja utilisé"),
    EMAIL_IS_INVALID(HttpStatus.BAD_REQUEST,"Cet email est invalide"),
    ACCOUNT_IS_ALREADY_ACTIVE(HttpStatus.FORBIDDEN,"Ce compte est déjà actif"),
    BAD_ACTIVATION_CODE(HttpStatus.BAD_REQUEST,"Code invalide, merci de retapper le code"),
    CODE_IS_ACTIVE(HttpStatus.FORBIDDEN,"Votre code est encore actif"),
    CODE_IS_ALREADY_SEND(HttpStatus.FORBIDDEN,"Votre code a deja etait envoye, merci de verifier vos email et vos spams"),
    BAD_PASSWORD(HttpStatus.FORBIDDEN,"Mot de passe invalide");

    private final HttpStatus status;
    private final String message;


    CustomExceptionMessage(HttpStatus status,String message) {

        this.message = message;
        this.status = status;

    }

    public static HttpStatus findByMessage(String message) {
        for (CustomExceptionMessage exceptionMessage : values()){
            if (exceptionMessage.getMessage().equals(message)){
                return exceptionMessage.getStatus();
            }
        }
        return HttpStatus.BAD_REQUEST;
    }
}
