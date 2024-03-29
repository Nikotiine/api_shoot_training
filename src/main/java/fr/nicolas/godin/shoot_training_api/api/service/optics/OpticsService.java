package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.interfaces.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.*;
import fr.nicolas.godin.shoot_training_api.database.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpticsService implements AdminInterface<OpticsDto, OpticsCreateDto> {

    private OpticsRepository opticsRepository;


    /**
     * Retroune la liste de toutes les optiquess
     * @return List<OpticsDto>
     */
    public List<OpticsDto> getAll() {

        return ModelMapperTool.mapList(this.opticsRepository.findAll(), OpticsDto.class);
    }


    public OpticsDto create(OpticsCreateDto newOptics) {
        try {

            Optics optics = ModelMapperTool.mapDto(newOptics, Optics.class);
            Optics saved = this.opticsRepository.save(optics);
            return ModelMapperTool.mapDto(saved, OpticsDto.class);

        } catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.OPTIC_MODEL_IS_EXIST.getMessage());
        }

    }

    @Override
    public OpticsDto update(OpticsDto opticsDto) {

        try {
            Optics optics = ModelMapperTool.mapDto(opticsDto,Optics.class);
            Optics saved = this.opticsRepository.save(optics);
            return ModelMapperTool.mapDto(saved,OpticsDto.class);
        }
        catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.OPTIC_MODEL_IS_EXIST.getMessage());
        }

    }

    @Override
    public List<OpticsDto> delete(int id) {

        try {

            Optics optics = this.opticsRepository.findById(id);
            optics.setActive(false);
            this.opticsRepository.save(optics);
            return this.getAll();
        } catch (NullPointerException e) {

            throw new CustomException(CustomExceptionMessage.NULL_POINTER_EXCEPTION.getMessage());
        }

    }


    @Override
    public long countTotalEntry() {

        return this.opticsRepository.count();
    }

    @Override
    public OpticsDto getLastEntry() {

        Optics optics = this.opticsRepository.findFirstByOrderByIdDesc();
        return ModelMapperTool.mapDto(optics, OpticsDto.class);
    }


    @Override
    public List<OpticsDto> getAllActive() {

        return ModelMapperTool.mapList(this.opticsRepository.findAllByActiveIsTrue(),OpticsDto.class);
    }
}
