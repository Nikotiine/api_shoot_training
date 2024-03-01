package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.FactoryDto;
import fr.nicolas.godin.shoot_training_api.api.dto.NewFactoryDto;
import fr.nicolas.godin.shoot_training_api.api.enums.FactoryType;
import fr.nicolas.godin.shoot_training_api.api.service.FactoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Factory",description = "Factory Controller")
@RequestMapping(value = "/api/factory",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class FactoryController {
    private FactoryService factoryService;
    @GetMapping("all-by-type")
    @ResponseBody
    public List<FactoryDto> getAllFactoryByType(@RequestParam(name = "type")FactoryType type) {
        return this.factoryService.getAllByType(type);
    }

    @PostMapping(value = "save")
    @ResponseBody
    public FactoryDto save(@Valid @RequestBody NewFactoryDto newFactoryDto){
        return this.factoryService.create(newFactoryDto);
    }
}
