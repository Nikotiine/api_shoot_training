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
    CODE_IS_ALREADY_USE(HttpStatus.BAD_REQUEST,"Vous avez deja valide votre code"),
    CODE_IS_ALREADY_SEND(HttpStatus.FORBIDDEN,"Votre code a deja etait envoye, merci de verifier vos email et vos spams"),
    BAD_PASSWORD(HttpStatus.FORBIDDEN,"Mot de passe invalide"),
    WEAPON_MODEL_IS_EXIST(HttpStatus.BAD_REQUEST,"Ce model d'arme existe deja"),
    OPTIC_MODEL_IS_EXIST(HttpStatus.BAD_REQUEST,"Cette lunette existe deja"),
    NULL_POINTER_EXCEPTION(HttpStatus.BAD_REQUEST,"Erreur de parametre dans la requete (ID inconnu)"),
    WEAPON_SETUP_IS_EXIST(HttpStatus.BAD_REQUEST,"Ce setup existe deja"),
    CALIBER_IS_EXIST(HttpStatus.BAD_REQUEST,"Ce calibre existe deja"),
    WEIGHT_IS_EXIST(HttpStatus.BAD_REQUEST,"Ce poids existe deja"),
    AMMUNITION_NAME_AND_FACTORY_EXIST(HttpStatus.BAD_REQUEST,"Ce model existe deja dans cette marque"),
    FACTORY_IS_EXIST(HttpStatus.BAD_REQUEST,"Cette marque existe deja");


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
