package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.interfaces.CommonInterface;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.TrainingSession;
import fr.nicolas.godin.shoot_training_api.database.repository.TrainingSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingSessionService implements CommonInterface<TrainingSessionDto, TrainingSessionCreateDto> {
    private TrainingSessionRepository trainingSessionRepository;

    /**
     * Retourne la liste T des element actif
     *
     * @return List<T>
     */
    @Override
    public List<TrainingSessionDto> getAllActive() {
        return null;
    }

    /**
     * Creation de l'objet T avec son Dto de creation D
     *
     * @param trainingSessionCreateDto D
     * @return T
     */
    @Override
    public TrainingSessionDto create(TrainingSessionCreateDto trainingSessionCreateDto) {
        try {
            TrainingSession trainingSession = ModelMapperTool.mapDto(trainingSessionCreateDto, TrainingSession.class);
            TrainingSession created = this.trainingSessionRepository.save(trainingSession);
            return ModelMapperTool.mapDto(created, TrainingSessionDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(CustomExceptionMessage.TRAINING_SESSION.getMessage());
        }

    }

    /**
     * Mise a jour de l'objet sous son format Dto
     *
     * @param trainingSessionDto T
     * @return T
     */
    @Override
    public TrainingSessionDto update(TrainingSessionDto trainingSessionDto) {
        return null;
    }

    /**
     * Suppression de l'objet avec son id en param
     *
     * @param id int
     * @return List<T>
     */
    @Override
    public List<TrainingSessionDto> delete(int id) {
        return null;
    }

    public List<TrainingSessionDto> getAllByUserId(int id) {

        return ModelMapperTool.mapList(this.trainingSessionRepository.findTrainingSessionBySetup_UserId(id), TrainingSessionDto.class);
    }
}
