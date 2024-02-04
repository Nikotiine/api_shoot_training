package fr.nicolas.godin.shoot_training_api.api.dto;





public class DtoDecoratorConfiguration {
    static final String NOT_NULL_MESSAGE ="Ne peut pas être nul";
    static final String NOT_BLACK_MESSAGE ="Ne peut pas être non rempli";
    static final String NOT_EMPTY_MESSAGE ="Ne peut pas être vide";
    static final String INVALID_EMAIL_MESSAGE ="Format d'email invalide";
    static final String EMAIL_REGEX= "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    static final int MIN_LENGTH_CODE=100000;
    static final int MAX_LENGTH_CODE=999999;
    static final String POSITIVE_OR_ZERO_MESSAGE = "Doit etre O ou positif";
}
