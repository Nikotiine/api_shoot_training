package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.ShooterEditDto;
import fr.nicolas.godin.shoot_training_api.api.dto.ShooterProfileDto;
import fr.nicolas.godin.shoot_training_api.api.service.ShooterService;
import fr.nicolas.godin.shoot_training_api.database.entity.Shooter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Shooter",description = "Shooter Controller")
@RequestMapping(value = "/api/shooter",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ShooterController {

    private ModelMapper modelMapper;
    private ShooterService shooterService;

    @PostMapping("edit")
    @ResponseBody
    public ShooterProfileDto edit(@RequestBody ShooterEditDto shooterEditDto) {

            Shooter shooter = this.shooterService.update(shooterEditDto);
            return this.modelMapper.map(shooter, ShooterProfileDto.class);

    }
}
