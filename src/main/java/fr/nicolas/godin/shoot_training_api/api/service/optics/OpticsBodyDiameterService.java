package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.dao.SimpleInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.OpticsBodyDiameterDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.OpticsBodyDiameter;
import fr.nicolas.godin.shoot_training_api.database.repository.OpticsBodyDiameterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpticsBodyDiameterService implements SimpleInterface<OpticsBodyDiameterDto> {
    private OpticsBodyDiameterRepository opticsBodyDiameterRepository;

    public List<OpticsBodyDiameterDto> getAll() {

        List<OpticsBodyDiameter> opticsBodyDiameter = (List<OpticsBodyDiameter>) this.opticsBodyDiameterRepository.findAll();
        return ModelMapperTool.mapList(opticsBodyDiameter,OpticsBodyDiameterDto.class);
    }
}
