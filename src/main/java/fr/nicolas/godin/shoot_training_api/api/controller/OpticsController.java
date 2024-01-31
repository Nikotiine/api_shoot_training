package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.NewOpticsDto;
import fr.nicolas.godin.shoot_training_api.api.dto.OpticsDataCollection;
import fr.nicolas.godin.shoot_training_api.api.dto.OpticsDto;
import fr.nicolas.godin.shoot_training_api.api.service.OpticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public OpticsDataCollection getOpticsDataCollection() {
        return this.opticsService.getDataCollection();
    }

    @PostMapping("new")
    @ResponseBody
    public OpticsDto newOptics(@RequestBody NewOpticsDto newOptics){
        return this.opticsService.save(newOptics);
    }
 }
