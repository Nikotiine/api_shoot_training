package fr.nicolas.godin.shoot_training_api.api.enums;

import lombok.Getter;

@Getter

public enum CodeMessageResponse {
    //Success
    REGISTER_SUCCESS(1,"Activez votre compte"),
    ACCOUNT_ACTIVATED(2,"Votre compte est actif"),
    NEW_CODE_SENT(3,"Nouveau code envoyé"),
    RESET_PASSWORD_CODE_SENT(4,"Code de réinitialisation envoyé"),
    NEW_PASSWORD_CHANGE(5,"Nouveau mot de passe active");

    private final int code;
    private final String message;

    CodeMessageResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
