package fr.nicolas.godin.shoot_training_api.configuration;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> expiredJWT(RuntimeException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(KEY, e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
    public ResponseEntity<Map<String,String>> invalidJwt(io.jsonwebtoken.ExpiredJwtException e) {

        Map<String, String> errors = new HashMap<>();
        errors.put(KEY, "Erreur de token");
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }
}
