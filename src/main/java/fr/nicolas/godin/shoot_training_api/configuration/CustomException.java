package fr.nicolas.godin.shoot_training_api.configuration;


public class CustomException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = 5162710183389128792L;
    public CustomException(String message){
        super(message);
    }
}
