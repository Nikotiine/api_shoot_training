package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.CaliberDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.Caliber;
import fr.nicolas.godin.shoot_training_api.database.repository.CaliberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CaliberService {
    private CaliberRepository caliberRepository;

    public List<CaliberDto> findAllCalibers() {
        List<Caliber> caliberList = (List<Caliber>) this.caliberRepository.findAll();
        return ModelMapperTool.mapList(caliberList, CaliberDto.class);
    }
}
