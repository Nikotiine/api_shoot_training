package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.CaliberCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.CaliberDto;
import fr.nicolas.godin.shoot_training_api.api.service.CaliberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "API_Caliber",description = "Caliber Controller")
@RequestMapping(value = "/api/caliber",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CaliberController {

    private CaliberService caliberService;
    @GetMapping("all")
    @ResponseBody
    public List<CaliberDto> getAllCalibers() {

        return this.caliberService.getAllActive();
    }

    @PostMapping("save")
    @ResponseBody
    public CaliberDto saveCaliber(@Valid @RequestBody CaliberCreateDto caliberCreateDto) {

        return this.caliberService.create(caliberCreateDto);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("edit")
    @ResponseBody
    public CaliberDto editCaliber(@Valid @RequestBody CaliberDto caliberDto) {

        return this.caliberService.update(caliberDto);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("delete")
    @ResponseBody
    public List<CaliberDto> disableCaliber(@RequestParam(name = "id") int id) {

        return this.caliberService.delete(id);
    }
}
