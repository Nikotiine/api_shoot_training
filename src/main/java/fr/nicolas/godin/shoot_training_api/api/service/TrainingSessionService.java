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
     * @return List<TrainingSessionDto>
     */
    @Override
    public List<TrainingSessionDto> getAllActive() {
        return null;
    }

    /**
     * Creation de l'objet TrainingSession avec son Dto de creation TrainingSessionCreateDto
     *
     * @param trainingSessionCreateDto TrainingSessionCreateDto
     * @return TrainingSessionDto
     */
    @Override
    @Transactional
    public TrainingSessionDto create(TrainingSessionCreateDto trainingSessionCreateDto) {
        try {

            TrainingSession trainingSession = ModelMapperTool.mapDto(trainingSessionCreateDto, TrainingSession.class);
            Set<AmmunitionSpeedHistoryCreateDto> ammunitionSpeedHistories = trainingSessionCreateDto.getSpeedHistories();
            Set<TrainingSessionGroupCreateDto> trainingSessionGroups = trainingSessionCreateDto.getTrainingSessionGroups();

            return this.getTrainingSession(ammunitionSpeedHistories, trainingSessionGroups, trainingSession);
        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.TRAINING_SESSION.getMessage());
        }

    }



    /**
     * Mise a jour de TrainingSession sous son format Dto
     *
     * @param trainingSessionDto TrainingSessionDto
     * @return TrainingSessionDto
     */
    @Override
    @Transactional
    public TrainingSessionDto update(TrainingSessionDto trainingSessionDto) {
        try {

            TrainingSession trainingSession = ModelMapperTool.mapDto(trainingSessionDto, TrainingSession.class);
            List<AmmunitionSpeedHistory> ammunitionSpeedHistoryList = this.ammunitionSpeedHistoryRepository.findAllByTrainingSessionId(trainingSession.getId());
            List<TrainingSessionGroup> trainingSessionGroupList = this.trainingSessionGroupRepository.findAllByTrainingSessionId(trainingSession.getId());
            if (!ammunitionSpeedHistoryList.isEmpty()) {
                this.ammunitionSpeedHistoryRepository.deleteAllByTrainingSessionId(trainingSessionDto.getId());
            }
            if (!trainingSessionGroupList.isEmpty()) {
                this.trainingSessionGroupRepository.deleteAllByTrainingSessionId(trainingSessionDto.getId());
            }

            return this.getTrainingSession(trainingSessionDto.getSpeedHistories(), trainingSessionDto.getTrainingSessionGroups(), trainingSession);
        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.TRAINING_SESSION.getMessage());
        }
    }

    /**
     * Suppression de la TrainingSessionDto avec son id en param
     *
     * @param id int
     * @return List<TrainingSessionDto> de l'utilisateur connecté
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

    /**
     * Retourne les sessions active de l'utilisateur connecté
     * @param id int id de l'user
     * @return List<TrainingSessionDto>
     */
    public List<TrainingSessionDto> getAllActiveByUserId(int id) {

        return ModelMapperTool.mapList(this.trainingSessionRepository.findTrainingSessionsByUserIdAndActiveIsTrueOrderByCreatedAtAsc(id), TrainingSessionDto.class);
    }

    /**
     * Retourne toutes les session de l'utilisateur active ou non
     * @param id int id de l'user
     * @return List<TrainingSessionDto>
     */
    public List<TrainingSessionDto> getAllByUserId(int id) {

        return ModelMapperTool.mapList(this.trainingSessionRepository.findTrainingSessionsByUserIdAndOrderByCreatedAtAsc(id), TrainingSessionDto.class);
    }

    /**
     * Retourne une session
     * @param id de la session
     * @return TrainingSessionDto
     */
    public TrainingSessionDto getById(int id) {
        try {
            TrainingSession trainingSession = this.trainingSessionRepository.findTrainingSessionById(id);
            return ModelMapperTool.mapDto(trainingSession, TrainingSessionDto.class);
        } catch (NullPointerException e) {

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }

    }


    /**
     * Enregistre en base de donne la nouvelle session ou met a jour si la session envoye a un ID
     * @param ammunitionSpeedHistoryList la liste des vitesses
     * @param trainingSessionGroupCreateDtoList la liste de groupement
     * @param trainingSession la session
     * @return TrainingSessionDto pour le retour au front
     */
    private TrainingSessionDto getTrainingSession(Set<AmmunitionSpeedHistoryCreateDto> ammunitionSpeedHistoryList, Set<TrainingSessionGroupCreateDto> trainingSessionGroupCreateDtoList, TrainingSession trainingSession) {
        Set<AmmunitionSpeedHistory> speedHistories = getAmmunitionSpeedHistories(ammunitionSpeedHistoryList, trainingSession);
        trainingSession.setSpeedHistories(speedHistories);


        Set<TrainingSessionGroup> trainingSessionGroups = getTrainingSessionGroups(trainingSessionGroupCreateDtoList, trainingSession);
        trainingSession.setTrainingSessionGroups(trainingSessionGroups);

        TrainingSession created = this.trainingSessionRepository.save(trainingSession);
        this.ammunitionSpeedHistoryRepository.saveAll(speedHistories);
        this.trainingSessionGroupRepository.saveAll(trainingSessionGroups);
        return ModelMapperTool.mapDto(created, TrainingSessionDto.class);
    }

    /**
     * Map le hashSet a partir de la  TrainingSessionCreateDto , les attibue la trainingSession pour la sauvegarde et retourne le HashSet sous Format d'objet hibernate
     * @param ammunitionSpeedHistoryCreateDto Set<AmmunitionSpeedHistoryCreateDto>
     * @param trainingSession TrainingSession
     * @return Set<AmmunitionSpeedHistory>
     */
    private Set<AmmunitionSpeedHistory> getAmmunitionSpeedHistories(Set<AmmunitionSpeedHistoryCreateDto> ammunitionSpeedHistoryCreateDto, TrainingSession trainingSession) {
        Set<AmmunitionSpeedHistory> speedHistories = new HashSet<>();
        for (AmmunitionSpeedHistoryCreateDto speedHistoryDto : ammunitionSpeedHistoryCreateDto) {
            AmmunitionSpeedHistory speedHistory = ModelMapperTool.mapDto(speedHistoryDto,AmmunitionSpeedHistory.class);
            speedHistory.setTrainingSession(trainingSession);
            speedHistories.add(speedHistory);
        }
        return speedHistories;
    }

    /**
     * Map le hashSet a partir de la  TrainingSessionCreateDto , les attibue la trainingSession pour la sauvegarde et retourne le HashSet sous Format d'objet hibernate
     * @param trainingSessionGroupCreateDto Set<TrainingSessionGroupCreateDto>
     * @param trainingSession TrainingSession
     * @return Set<TrainingSessionGroup>
     */
    private Set<TrainingSessionGroup> getTrainingSessionGroups(Set<TrainingSessionGroupCreateDto> trainingSessionGroupCreateDto, TrainingSession trainingSession) {
        Set<TrainingSessionGroup> trainingSessionGroups = new HashSet<>();
        for (TrainingSessionGroupCreateDto groupDto : trainingSessionGroupCreateDto) {
            TrainingSessionGroup sessionGroup = ModelMapperTool.mapDto(groupDto, TrainingSessionGroup.class);
            sessionGroup.setTrainingSession(trainingSession);
            trainingSessionGroups.add(sessionGroup);
        }
        return trainingSessionGroups;
    }
}
