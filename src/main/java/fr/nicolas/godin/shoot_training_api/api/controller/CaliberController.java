package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.CaliberDto;
import fr.nicolas.godin.shoot_training_api.api.service.CaliberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Caliber",description = "Caliber Controller")
@RequestMapping(value = "/api/caliber",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CaliberController {

    private CaliberService caliberService;
    @GetMapping("all")
    public List<CaliberDto> getAllCalibers(){
        return this.caliberService.findAllCalibers();
    }
}
