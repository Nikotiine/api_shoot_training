package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.Token;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;

import java.util.Date;
import java.util.Map;



@Service
@AllArgsConstructor
public class JwtService {

    private final AuthenticationService authenticationService;


    public Token generate(String email) {
        Shooter shooter = this.authenticationService.loadUserByUsername(email);
        return this.generateToken(shooter);
    }

    private Token generateToken(Shooter shooter) {
        Map<String,?> claims = Map.of(
                "role",shooter.getRole(),
                "id",shooter.getId()
        );

        Date issuedAt =  new Date();
        int expiration = 60 * 1000* 10;

        String jwt = Jwts
                .builder()
                .issuedAt(new Date())
                .expiration(new Date(issuedAt.getTime() + expiration))
                .claims(claims)
                .subject(shooter.getEmail())
                .signWith(getKey())
                .compact();
        return new Token(jwt);
    }

    private Key getKey() {
        Dotenv dotenv = Dotenv.load();
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(dotenv.get("JWT_SECRET_KEY")));

    }
}
