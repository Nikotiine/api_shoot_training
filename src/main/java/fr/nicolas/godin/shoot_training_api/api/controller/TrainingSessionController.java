package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionGroupByMouthDto;
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

    @GetMapping(value = "active/by/user")
    @ResponseBody
    public List<TrainingSessionDto> getActiveTrainingSessionByUserId(@RequestParam(name = "id")int id) {

        return this.trainingSessionService.getAllActiveByUserId(id);
    }

    @GetMapping(value = "find/one")
    @ResponseBody
    public TrainingSessionDto getTrainingSessionById(@RequestParam(name = "id")int id) {

        return this.trainingSessionService.getById(id);
    }

    @GetMapping(value = "all/by/user")
    @ResponseBody
    public List<TrainingSessionDto> getAllTrainingSessionByUserId(@RequestParam(name = "id")int id) {

        return this.trainingSessionService.getAllByUserId(id);
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public List<TrainingSessionDto> deleteTrainingSession(@RequestParam(name = "id")int id) {

        return this.trainingSessionService.delete(id);
    }

    @PutMapping(value = "edit")
    @ResponseBody
    public TrainingSessionDto updateTrainingSession(@Valid @RequestBody TrainingSessionDto trainingSessionDto) {

        return this.trainingSessionService.update(trainingSessionDto);
    }

    @GetMapping(value = "all/user/session/by-mouth")
    @ResponseBody
    public TrainingSessionGroupByMouthDto getTrainingSessionByUserIdGroupByMouth(@RequestParam(name = "id")int id){
       return this.trainingSessionService.getSessionByUserIdGroupByMouth(id);
    }
}
