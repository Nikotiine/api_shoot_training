package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.service.*;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionWeight;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Ammunition",description = "Ammunition Controller")
@RequestMapping(value = "/api/ammunition",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AmmunitionController {

    private AmmunitionService ammunitionService;


    @PostMapping("save")
    @ResponseBody
    public AmmunitionDto newAmmunition(@Valid @RequestBody AmmunitionCreateDto ammunitionCreateDto) {

        return this.ammunitionService.create(ammunitionCreateDto);
    }


    @GetMapping("all")
    @ResponseBody
    public List<AmmunitionDto> getAllAmmunition() {

        return this.ammunitionService.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("delete")
    @ResponseBody
    public List<AmmunitionDto> disableAmmunition(@RequestParam(name = "id") int id) {

        return this.ammunitionService.delete(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("edit")
    @ResponseBody
    public AmmunitionDto editAmmunition(@Valid @RequestBody AmmunitionDto ammunitionDto) {

        return this.ammunitionService.update(ammunitionDto);
    }

}
