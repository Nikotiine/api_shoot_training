package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.interfaces.SimpleInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.OpticsOutletDiameterDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.OpticsOutletDiameter;
import fr.nicolas.godin.shoot_training_api.database.repository.OpticsOutletDiameterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpticsOutletDiameterService implements SimpleInterface<OpticsOutletDiameterDto> {

    private OpticsOutletDiameterRepository opticsOutletDiameterRepository;

    public List<OpticsOutletDiameterDto> getAll(){
        List<OpticsOutletDiameter> opticsOutletDiameterList = (List<OpticsOutletDiameter>) this.opticsOutletDiameterRepository.findAll();
        return ModelMapperTool.mapList(opticsOutletDiameterList, OpticsOutletDiameterDto.class);
    }
}
