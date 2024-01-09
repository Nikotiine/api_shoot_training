package fr.nicolas.godin.shoot_training_api.api.enums;

import lombok.Getter;

@Getter

public enum CodeMessageResponse {
    //Success
    REGISTER_SUCCESS(1,"Activez votre compte"),
    ACCOUNT_ACTIVATED(2,"Votre compte est actif"),
    NEW_CODE_SENT(3,"Nouveau code envoyé"),
    //Error
    EMAIL_IS_ALREADY_USE(101,"Cet email est deja utilisé"),
    ACCOUNT_IS_ALREADY_ACTIVE(102,"Ce compte est déjà actif"),
    CODE_IS_OUT_OF_TIME(103,"Le code n'est plus valide"),
    BAD_ACTIVATION_CODE(104,"Code invalide, merci de retapper le code"),
    EMAIL_IS_INVALID(105,"Cet email est invalide");;

    private final int code;
    private final String message;

    CodeMessageResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
