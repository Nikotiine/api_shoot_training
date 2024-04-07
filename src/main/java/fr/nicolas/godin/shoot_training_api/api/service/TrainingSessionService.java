package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionSpeedHistoryCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.interfaces.CommonInterface;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.Ammunition;
import fr.nicolas.godin.shoot_training_api.database.entity.AmmunitionSpeedHistory;
import fr.nicolas.godin.shoot_training_api.database.entity.TrainingSession;
import fr.nicolas.godin.shoot_training_api.database.entity.UserWeaponSetup;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionSpeedHistoryRepository;
import fr.nicolas.godin.shoot_training_api.database.repository.TrainingSessionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TrainingSessionService implements CommonInterface<TrainingSessionDto, TrainingSessionCreateDto> {
    private TrainingSessionRepository trainingSessionRepository;
    private AmmunitionSpeedHistoryRepository ammunitionSpeedHistoryRepository;

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
    @Transactional
    public TrainingSessionDto create(TrainingSessionCreateDto trainingSessionCreateDto) {

        try {

            TrainingSession trainingSession = ModelMapperTool.mapDto(trainingSessionCreateDto, TrainingSession.class);

            Set<AmmunitionSpeedHistory> speedHistories = new HashSet<>();
            for (AmmunitionSpeedHistoryCreateDto speedHistoryDto : trainingSessionCreateDto.getSpeedHistories()) {
                AmmunitionSpeedHistory speedHistory = ModelMapperTool.mapDto(speedHistoryDto,AmmunitionSpeedHistory.class);
                speedHistory.setTrainingSession(trainingSession);
                speedHistories.add(speedHistory);
            }
            trainingSession.setSpeedHistories(speedHistories);

            TrainingSession created = this.trainingSessionRepository.save(trainingSession);
            this.ammunitionSpeedHistoryRepository.saveAll(speedHistories);
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
