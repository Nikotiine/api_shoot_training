package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.CredentialsDto;
import fr.nicolas.godin.shoot_training_api.api.dto.ErrorMessage;
import fr.nicolas.godin.shoot_training_api.api.dto.ShooterProfileDto;
import fr.nicolas.godin.shoot_training_api.api.dto.Token;
import fr.nicolas.godin.shoot_training_api.api.security.JwtService;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(name = "Authentication",description = "Authentication Controller")
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private ModelMapper modelMapper;
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody CredentialsDto credentials) {

        try {

            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(), credentials.getPassword()
                    )
            );
            Token token = this.jwtService.generate(credentials.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(token);


        } catch (UsernameNotFoundException e) {

            return  ResponseEntity.status(BAD_REQUEST).body(new ErrorMessage(2,"Email is not valid"));

        }

    }

    @GetMapping("me")
    public ResponseEntity<ShooterProfileDto> me(){
        // Lors de la connection retourne le profil user et permet de verifier que le token est bon
        Shooter shooter =(Shooter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ShooterProfileDto profile = this.modelMapper.map(shooter,ShooterProfileDto.class);
        return ResponseEntity.status(OK).body(profile);

    }
}
