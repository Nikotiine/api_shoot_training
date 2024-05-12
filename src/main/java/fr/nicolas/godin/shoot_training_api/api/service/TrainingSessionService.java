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
import java.util.NoSuchElementException;
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

            Set<AmmunitionSpeedHistory> speedHistories = getAmmunitionSpeedHistories(trainingSessionCreateDto.getSpeedHistories(), trainingSession);
            trainingSession.setSpeedHistories(speedHistories);

            Set<TrainingSessionGroup> trainingSessionGroups = getTrainingSessionGroups(trainingSessionCreateDto.getTrainingSessionGroups(), trainingSession);
            trainingSession.setTrainingSessionGroups(trainingSessionGroups);

            TrainingSession created = this.trainingSessionRepository.save(trainingSession);
            this.ammunitionSpeedHistoryRepository.saveAll(speedHistories);
            this.trainingSessionGroupRepository.saveAll(trainingSessionGroups);
            return ModelMapperTool.mapDto(created, TrainingSessionDto.class);
        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.TRAINING_SESSION.getMessage());
        }

    }

    private Set<AmmunitionSpeedHistory> getAmmunitionSpeedHistories(Set<AmmunitionSpeedHistoryCreateDto> ammunitionSpeedHistoryCreateDto, TrainingSession trainingSession) {
        Set<AmmunitionSpeedHistory> speedHistories = new HashSet<>();
        for (AmmunitionSpeedHistoryCreateDto speedHistoryDto : ammunitionSpeedHistoryCreateDto) {
            AmmunitionSpeedHistory speedHistory = ModelMapperTool.mapDto(speedHistoryDto,AmmunitionSpeedHistory.class);
            speedHistory.setTrainingSession(trainingSession);
            speedHistories.add(speedHistory);
        }
        return speedHistories;
    }

    private Set<TrainingSessionGroup> getTrainingSessionGroups(Set<TrainingSessionGroupCreateDto> trainingSessionGroupCreateDto, TrainingSession trainingSession) {
        Set<TrainingSessionGroup> trainingSessionGroups = new HashSet<>();
        for (TrainingSessionGroupCreateDto groupDto : trainingSessionGroupCreateDto) {
            TrainingSessionGroup sessionGroup = ModelMapperTool.mapDto(groupDto, TrainingSessionGroup.class);
            sessionGroup.setTrainingSession(trainingSession);
            trainingSessionGroups.add(sessionGroup);
        }
        return trainingSessionGroups;
    }


    /**
     * Mise a jour de l'objet sous son format Dto
     *
     * @param trainingSessionDto T
     * @return T
     */
    @Override
    @Transactional
    public TrainingSessionDto update(TrainingSessionDto trainingSessionDto) {
        try {
            List<AmmunitionSpeedHistory> ammunitionSpeedHistoryList = this.ammunitionSpeedHistoryRepository.findAllByTrainingSessionId(trainingSessionDto.getId());
            List<TrainingSessionGroup> trainingSessionGroupList = this.trainingSessionGroupRepository.findAllByTrainingSessionId(trainingSessionDto.getId());
            if (!ammunitionSpeedHistoryList.isEmpty()){
                this.ammunitionSpeedHistoryRepository.deleteAllByTrainingSessionId(trainingSessionDto.getId());
            }
            if (!trainingSessionGroupList.isEmpty()){
                this.trainingSessionGroupRepository.deleteAllByTrainingSessionId(trainingSessionDto.getId());
            }


            TrainingSession trainingSession = ModelMapperTool.mapDto(trainingSessionDto, TrainingSession.class);
            Set<AmmunitionSpeedHistory> speedHistories = getAmmunitionSpeedHistories(trainingSessionDto.getSpeedHistories(), trainingSession);
            trainingSession.setSpeedHistories(speedHistories);
            Set<TrainingSessionGroup> trainingSessionGroups = getTrainingSessionGroups(trainingSessionDto.getTrainingSessionGroups(), trainingSession);
            trainingSession.setTrainingSessionGroups(trainingSessionGroups);

            TrainingSession updated = this.trainingSessionRepository.save(trainingSession);
            this.ammunitionSpeedHistoryRepository.saveAll(speedHistories);
            this.trainingSessionGroupRepository.saveAll(trainingSessionGroups);
            return ModelMapperTool.mapDto(updated, TrainingSessionDto.class);

        } catch (DataIntegrityViolationException e) {
            throw new CustomException(CustomExceptionMessage.TRAINING_SESSION.getMessage());
        }

    }

    /**
     * Suppression de l'objet avec son id en param
     *
     * @param id int
     * @return List<T>
     */
    @Override
    public List<TrainingSessionDto> delete(int id) {

        try {
            TrainingSession trainingSession = this.trainingSessionRepository.findTrainingSessionById(id);
            trainingSession.setActive(false);
            this.trainingSessionRepository.save(trainingSession);
            return this.getAllActiveByUserId(trainingSession.getSetup().getUser().getId());
        } catch (NoSuchElementException e) {

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }

    }

    public List<TrainingSessionDto> getAllActiveByUserId(int id) {

        return ModelMapperTool.mapList(this.trainingSessionRepository.findTrainingSessionsByUserIdAAndActiveIsTrue(id), TrainingSessionDto.class);
    }

    public List<TrainingSessionDto> getAllByUserId(int id) {

        return ModelMapperTool.mapList(this.trainingSessionRepository.findTrainingSessionBySetup_UserId(id), TrainingSessionDto.class);
    }

    public TrainingSessionDto getById(int id) {
        try {
            TrainingSession trainingSession = this.trainingSessionRepository.findTrainingSessionById(id);
            return ModelMapperTool.mapDto(trainingSession, TrainingSessionDto.class);
        } catch (NullPointerException e) {

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }

    }
}
