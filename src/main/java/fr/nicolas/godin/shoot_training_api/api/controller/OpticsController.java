package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.OpticsDataCollection;
import fr.nicolas.godin.shoot_training_api.api.dto.OpticsDto;
import fr.nicolas.godin.shoot_training_api.api.service.OpticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Optics",description = "Optics Controller")
@RequestMapping(value = "/api/optics",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OpticsController {

    private OpticsService opticsService;

    @GetMapping("all")
    @ResponseBody
    public List<OpticsDto> getAllOptics(){
        return this.opticsService.getAll();
    }

    @GetMapping(value = "data-collection")
    @ResponseBody
    public OpticsDataCollection getDataCollection() {
        return this.opticsService.getDataCollection();
    }
}
