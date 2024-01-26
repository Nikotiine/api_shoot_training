package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.*;
import fr.nicolas.godin.shoot_training_api.database.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpticsService {

    private OpticsRepository opticsRepository;
    private OpticsFactoryRepository opticsFactoryRepository;
    private OpticsBodyDiameterRepository opticsBodyDiameterRepository;
    private OpticsClickTypeRepository opticsClickTypeRepository;
    private OpticsFocalPlaneRepository opticsFocalPlaneRepository;
    private OpticsOutletDiameterRepository opticsOutletDiameterRepository;

    public List<OpticsDto> getAll() {
        List<Optics> opticsList = (List<Optics>) this.opticsRepository.findAll();
        return ModelMapperTool.mapList(opticsList, OpticsDto.class);
    }

    public OpticsDataCollection getDataCollection() {
        List<OpticsFactory> opticsFactoryList = (List<OpticsFactory>) this.opticsFactoryRepository.findAll();
        List<OpticsFactoryDto> opticsFactoryDtoList = ModelMapperTool.mapList(opticsFactoryList, OpticsFactoryDto.class);

        List<OpticsBodyDiameter> opticsBodyDiameterList = (List<OpticsBodyDiameter>) this.opticsBodyDiameterRepository.findAll();
        List<OpticsBodyDiameterDto> opticsBodyDiameterDtoList = ModelMapperTool.mapList(opticsBodyDiameterList, OpticsBodyDiameterDto.class);

        List<OpticsClickType> opticsClickTypesList = (List<OpticsClickType>) this.opticsClickTypeRepository.findAll();
        List<OpticsClickTypeDto> opticsClickTypeDtoList = ModelMapperTool.mapList(opticsClickTypesList, OpticsClickTypeDto.class);

        List<OpticsFocalPlane> opticsFocalPlaneList = (List<OpticsFocalPlane>) this.opticsFocalPlaneRepository.findAll();
        List<OpticsFocalPlaneDto> opticsFocalPlaneDtoList = ModelMapperTool.mapList(opticsFocalPlaneList, OpticsFocalPlaneDto.class);

        List<OpticsOutletDiameter> opticsOutletDiameterList = (List<OpticsOutletDiameter>) this.opticsOutletDiameterRepository.findAll();
        List<OpticsOutletDiameterDto> opticsOutletDiameterDtos = ModelMapperTool.mapList(opticsOutletDiameterList, OpticsOutletDiameterDto.class);

        return new OpticsDataCollection(opticsFactoryDtoList,opticsBodyDiameterDtoList,opticsClickTypeDtoList,opticsFocalPlaneDtoList,opticsOutletDiameterDtos);
    }
}
