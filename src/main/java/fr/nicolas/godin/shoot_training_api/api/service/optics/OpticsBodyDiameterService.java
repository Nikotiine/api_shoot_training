package fr.nicolas.godin.shoot_training_api.api.service.optics;

import fr.nicolas.godin.shoot_training_api.api.interfaces.SimpleInterface;
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

        return ModelMapperTool.mapList(this.opticsBodyDiameterRepository.findAll(),OpticsBodyDiameterDto.class);
    }
}
