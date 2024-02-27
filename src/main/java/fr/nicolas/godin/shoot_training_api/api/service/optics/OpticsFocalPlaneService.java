package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.dao.SimpleInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.OpticsFocalPlaneDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.OpticsFocalPlane;
import fr.nicolas.godin.shoot_training_api.database.repository.OpticsFocalPlaneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpticsFocalPlaneService implements SimpleInterface<OpticsFocalPlaneDto> {
    private OpticsFocalPlaneRepository opticsFocalPlaneRepository;

    public List<OpticsFocalPlaneDto> getAll(){
        List<OpticsFocalPlane>  opticsFocalPlaneList = (List<OpticsFocalPlane>) this.opticsFocalPlaneRepository.findAll();
        return ModelMapperTool.mapList(opticsFocalPlaneList, OpticsFocalPlaneDto.class);
    }
}
