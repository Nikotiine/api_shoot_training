package fr.nicolas.godin.shoot_training_api.configuration;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomRestExceptionHandler{

    private static final String KEY = "message";

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> invalidCredential(AuthenticationException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(KEY, "Indentifiants invalides");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> invalidCredential(BadCredentialsException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(KEY, e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> customException(CustomException e) {
        Map<String, String> errors = new HashMap<>();
        HttpStatus status = CustomExceptionMessage.findByMessage(e.getMessage());
        errors.put(KEY, e.getMessage());
        return new ResponseEntity<>(errors, status);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String,String>> expiredJWT(ExpiredJwtException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(KEY, "Token expiré");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
