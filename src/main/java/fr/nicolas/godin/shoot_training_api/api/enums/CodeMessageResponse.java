package fr.nicolas.godin.shoot_training_api.api.enums;

import lombok.Getter;

@Getter

public enum CodeMessageResponse {
    //Success
    REGISTER_SUCCESS(1,"Please active your account"),
    ACCOUNT_ACTIVATED(2,"Your account is active"),
    NEW_CODE_SENT(3,"New code sent"),
    //Error
    EMAIL_IS_ALREADY_USE(101,"Email is used"),
    ACCOUNT_IS_ALREADY_ACTIVE(102,"Account is active"),
    CODE_IS_OUT_OF_TIME(103,"Code is out of time"),
    BAD_ACTIVATION_CODE(104,"Code is invalid"),
    EMAIL_IS_INVALID(105,"Email is invalid");;

    private final int code;
    private final String message;

    CodeMessageResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
