package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
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
        List<Optics> opticsList = (List<Optics>) this.opticsRepository.findAll();
        return ModelMapperTool.mapList(opticsList, OpticsDto.class);
    }

   /* public OpticsDataCollection getDataCollection() {
        List<OpticsFactory> opticsFactoryList = (List<OpticsFactory>) this.opticsFactoryRepository.findAll();
        List<OpticsFactoryDto> opticsFactoryDtoList = ModelMapperTool.mapList(opticsFactoryList, OpticsFactoryDto.class);

        List<OpticsBodyDiameter> opticsBodyDiameterList = (List<OpticsBodyDiameter>) this.opticsBodyDiameterRepository.findAll();
        List<OpticsBodyDiameterDto> opticsBodyDiameterDtoList = ModelMapperTool.mapList(opticsBodyDiameterList, OpticsBodyDiameterDto.class);

        List<OpticsUnit> opticsopticsUnitsList = (List<OpticsUnit>) this.opticsUnitRepository.findAll();
        List<OpticsUnitDto> opticsUnitDtoList = ModelMapperTool.mapList(opticsopticsUnitsList, OpticsUnitDto.class);

        List<OpticsFocalPlane> opticsFocalPlaneList = (List<OpticsFocalPlane>) this.opticsFocalPlaneRepository.findAll();
        List<OpticsFocalPlaneDto> opticsFocalPlaneDtoList = ModelMapperTool.mapList(opticsFocalPlaneList, OpticsFocalPlaneDto.class);

        List<OpticsOutletDiameter> opticsOutletDiameterList = (List<OpticsOutletDiameter>) this.opticsOutletDiameterRepository.findAll();
        List<OpticsOutletDiameterDto> opticsOutletDiameterDtoList = ModelMapperTool.mapList(opticsOutletDiameterList, OpticsOutletDiameterDto.class);

        return new OpticsDataCollection(opticsFactoryDtoList,opticsBodyDiameterDtoList, opticsUnitDtoList,opticsFocalPlaneDtoList,opticsOutletDiameterDtoList);
    }*/

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
    public OpticsDto update(OpticsDto updateObjectDto) {
        return null;
    }

    @Override
    public List<OpticsDto> delete(OpticsDto deleteObjectDto) {
        return null;
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
        List<Optics> optics = (List<Optics>) this.opticsRepository.findAllByActiveIsTrue();
        return ModelMapperTool.mapList(optics,OpticsDto.class);
    }
}
