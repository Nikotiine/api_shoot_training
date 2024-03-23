package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.CaliberCreateDto;
import fr.nicolas.godin.shoot_training_api.api.dto.CaliberDto;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.Caliber;
import fr.nicolas.godin.shoot_training_api.database.repository.CaliberRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CaliberService implements AdminInterface<CaliberDto, CaliberCreateDto> {
    private CaliberRepository caliberRepository;


    /**
     * Compte le nombre d'entree total en base de donnée
     *
     * @return long
     */
    @Override
    public long countTotalEntry() {
        return this.caliberRepository.count();
    }

    /**
     * Retourne la derniere entree de la base de donnée
     *
     * @return T
     */
    @Override
    public CaliberDto getLastEntry() {
        Caliber caliber = this.caliberRepository.findFirstByOrderByIdDesc();
        return ModelMapperTool.mapDto(caliber,CaliberDto.class);
    }

    /**
     * Retourne la liste complete de T actif ou non
     *
     * @return List<T>
     */
    @Override
    public List<CaliberDto> getAll() {

        return ModelMapperTool.mapList(this.caliberRepository.findAll(), CaliberDto.class);
    }

    @Override
    public List<CaliberDto> getAllActive() {

        return ModelMapperTool.mapList(this.caliberRepository.findAllByActiveIsTrue(),CaliberDto.class);

    }

    @Override
    public CaliberDto create(CaliberCreateDto newObjectDto) {

        try {

            Caliber entity = ModelMapperTool.mapDto(newObjectDto,Caliber.class);
            Caliber saved = this.caliberRepository.save(entity);
            return ModelMapperTool.mapDto(saved,CaliberDto.class);

        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.CALIBER_IS_EXIST.getMessage());
        }

    }

    @Override
    public CaliberDto update(CaliberDto caliberDto) {

        try {

            Caliber caliber = ModelMapperTool.mapDto(caliberDto,Caliber.class);
            Caliber saved = this.caliberRepository.save(caliber);
            return ModelMapperTool.mapDto(saved,CaliberDto.class);

        } catch (DataIntegrityViolationException e) {

            throw new CustomException(CustomExceptionMessage.CALIBER_IS_EXIST.getMessage());
        }

    }

    @Override
    public List<CaliberDto> delete(int id) {

        Caliber caliber = this.caliberRepository.findById(id);
        caliber.setActive(false);
        this.caliberRepository.save(caliber);
        return this.getAll();
    }
}
