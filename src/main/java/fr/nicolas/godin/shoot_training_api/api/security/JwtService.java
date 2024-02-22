package fr.nicolas.godin.shoot_training_api.api.security;

import fr.nicolas.godin.shoot_training_api.api.dto.Token;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.service.AuthenticationService;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


@Service
@AllArgsConstructor
public class JwtService {

   private final AuthenticationService authenticationService;

    /**
     * Appele la methode pour generer le token lors de la connection de l'utilisateur
     * @param email String email de  l'utilisateur
     * @return token Token
     */
    public Token generate(String email) {

        User user = this.authenticationService.loadUserByUsername(email);
        return this.generateToken(user);

    }

    /**
     * Recupere l'utilisateur associe au token passer en parametre
     * @param token Token
     * @return l'objet utilisateur sous forme de UserDetails (springboot security)
     */
    public UserDetails getUserWithTokenPayload(String token){

        String userName = this.getClaim(token,Claims::getSubject);
        return this.authenticationService.loadUserByUsername(userName);

    }


    /**
     * Verifie la date de validite du token
     * @param token Token
     * @return boolean
     */
    public boolean verifyTokenValidity(String token) {

        Date validityDate = this.getClaim(token, Claims::getExpiration);
        return validityDate.before(new Date());

    }

    /**
     * Methode generique pour deconstruire le token
     * @param token String
     * @param function fonction generique
     * @return La classe generique passer en param
     * @param <T> Objet Generique
     */
    private <T> T getClaim(String token, Function<Claims,T> function) {

            Claims claims = getAllClaims(token);
            return function.apply(claims);

    }

    /**
     * Deconstuction du token
     * @param token String
     * @return les Claims du token
     */
    private Claims getAllClaims(String token) {

            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();


    }


    /**
     * Genere le token 
     * @param user Utilisateur connecter
     * @return Token
     */
    private Token generateToken(User user) {

        Map<String,?> claims = Map.of(
                "role", user.getRole(),
                "id", user.getId()
        );

        Date issuedAt =  new Date();
        // Temps d'expiration du token
        int timeOfExpirationInMinute = 120;
        final int oneMinuteInMillisecond =  60 * 1000;
        int expiration = oneMinuteInMillisecond * timeOfExpirationInMinute;

        String jwt = Jwts
                .builder()
                .issuedAt(new Date())
                .expiration(new Date(issuedAt.getTime() + expiration))
                .claims(claims)
                .subject(user.getEmail())
                .signWith(getKey())
                .compact();
        return new Token(jwt);

    }

    /**
     * Genere la clef de cryptage pour la signature du token
     * @return SecretKey clef Secrete
     */
    private SecretKey getKey() {

        // VA chercher le fichier .env
        Dotenv dotenv = Dotenv.load();
        byte[] secretKeyBytes = dotenv.get("JWT_SECRET_KEY").getBytes();
        return Keys.hmacShaKeyFor(secretKeyBytes);


    }


}
