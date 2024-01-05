package fr.nicolas.godin.shoot_training_api.configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomRestExceptionHandler{
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> invalidCredential(AuthenticationException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "invalid credentials");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
