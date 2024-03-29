package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import fr.nicolas.godin.shoot_training_api.database.entity.*;
import fr.nicolas.godin.shoot_training_api.database.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpticsService {

    private OpticsRepository opticsRepository;
    private OpticsFactoryRepository opticsFactoryRepository;
    private OpticsBodyDiameterRepository opticsBodyDiameterRepository;
    private OpticsUnitRepository opticsUnitRepository;
    private OpticsFocalPlaneRepository opticsFocalPlaneRepository;
    private OpticsOutletDiameterRepository opticsOutletDiameterRepository;
    private ModelMapper modelMapper;

    public List<OpticsDto> getAll() {
        List<Optics> opticsList = (List<Optics>) this.opticsRepository.findAll();
        return ModelMapperTool.mapList(opticsList, OpticsDto.class);
    }

    public OpticsDataCollection getDataCollection() {
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
    }

    public OpticsDto save(NewOpticsDto newOptics) {
        try {

            Optics optics = this.modelMapper.map(newOptics, Optics.class);
            Optics saved = this.opticsRepository.save(optics);
            return this.modelMapper.map(saved, OpticsDto.class);

        } catch (DataIntegrityViolationException e){

            throw new CustomException(CustomExceptionMessage.WEAPON_MODEL_IS_EXIST.getMessage());
        }

    }
}
