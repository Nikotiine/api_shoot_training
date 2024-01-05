package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.CredentialsDto;
import fr.nicolas.godin.shoot_training_api.api.dto.ShooterProfileDto;
import fr.nicolas.godin.shoot_training_api.api.dto.Token;
import fr.nicolas.godin.shoot_training_api.api.security.JwtService;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(name = "Authentication",description = "Authentication Controller")
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private ModelMapper modelMapper;
    @PostMapping(value = "login",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Token login(@Valid @RequestBody CredentialsDto credentials) {

    this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(), credentials.getPassword()
            )
    );
    return this.jwtService.generate(credentials.getEmail());


    }

    @GetMapping("me")
    public ResponseEntity<ShooterProfileDto> me(){
        // Lors de la connection retourne le profil user et permet de verifier que le token est bon
        Shooter shooter =(Shooter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ShooterProfileDto profile = this.modelMapper.map(shooter,ShooterProfileDto.class);
        return ResponseEntity.status(OK).body(profile);

    }
}
