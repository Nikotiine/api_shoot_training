package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.AmmunitionSpeedHistoryCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionDto;
import fr.nicolas.godin.shoot_training_api.api.dto.TrainingSessionGroupCreateDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.interfaces.CommonInterface;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.*;
import fr.nicolas.godin.shoot_training_api.database.repository.AmmunitionSpeedHistoryRepository;
import fr.nicolas.godin.shoot_training_api.database.repository.TrainingSessionGroupRepository;
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
    private TrainingSessionGroupRepository trainingSessionGroupRepository;

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
     * Creation de l'objet TrainingSession avec son Dto de creation D
     *
     * @param trainingSessionCreateDto TrainingSessionCreateDto
     * @return T
     */
    @Override
    @Transactional
    public TrainingSessionDto create(TrainingSessionCreateDto trainingSessionCreateDto) {

        try {

            TrainingSession trainingSession = ModelMapperTool.mapDto(trainingSessionCreateDto, TrainingSession.class);

            // Enregistrement de vitesse de munition renseigner pdt la session
            Set<AmmunitionSpeedHistory> speedHistories = new HashSet<>();
            for (AmmunitionSpeedHistoryCreateDto speedHistoryDto : trainingSessionCreateDto.getSpeedHistories()) {
                AmmunitionSpeedHistory speedHistory = ModelMapperTool.mapDto(speedHistoryDto,AmmunitionSpeedHistory.class);
                speedHistory.setTrainingSession(trainingSession);
                speedHistories.add(speedHistory);
            }
            trainingSession.setSpeedHistories(speedHistories);

            // Enregistemrnt des groupement de tir pdt la session
            Set<TrainingSessionGroup> trainingSessionGroups = new HashSet<>();
            for (TrainingSessionGroupCreateDto groupDto : trainingSessionCreateDto.getTrainingSessionGroups()) {
                TrainingSessionGroup sessionGroup = ModelMapperTool.mapDto(groupDto, TrainingSessionGroup.class);
                sessionGroup.setTrainingSession(trainingSession);
                trainingSessionGroups.add(sessionGroup);
            }

            trainingSession.setTrainingSessionGroups(trainingSessionGroups);
            TrainingSession created = this.trainingSessionRepository.save(trainingSession);
            this.ammunitionSpeedHistoryRepository.saveAll(speedHistories);
            this.trainingSessionGroupRepository.saveAll(trainingSessionGroups);
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
