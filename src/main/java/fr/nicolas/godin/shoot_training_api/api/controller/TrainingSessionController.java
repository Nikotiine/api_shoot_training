package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionDto;
import fr.nicolas.godin.shoot_training_api.api.service.TrainingSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "API_TrainingSession",description = "Training Session Controller")
@RequestMapping(value = "/api/training/session",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TrainingSessionController {
    private TrainingSessionService trainingSessionService;

    @PostMapping(value = "save")
    @ResponseBody
    public TrainingSessionDto createTrainingSession(@Valid @RequestBody TrainingSessionCreateDto trainingSessionCreateDto) {

        return this.trainingSessionService.create(trainingSessionCreateDto);
    }

    @GetMapping(value = "by-user")
    @ResponseBody
    public List<TrainingSessionDto> getTrainingSessionByUserId(@RequestParam(name = "id")int id) {

        return this.trainingSessionService.getAllByUserId(id);
    }
}
