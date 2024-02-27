package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dao.AdminInterface;
import fr.nicolas.godin.shoot_training_api.api.dto.CaliberDto;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.Caliber;
import fr.nicolas.godin.shoot_training_api.database.repository.CaliberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CaliberService implements AdminInterface<CaliberDto,Caliber> {
    private CaliberRepository caliberRepository;


    /**
     * Compte le nombre d'entree total en base de donnée
     *
     * @return long
     */
    @Override
    public long countTotalEntry() {
        return 0;
    }

    /**
     * Retourne la derniere entree de la base de donnée
     *
     * @return T
     */
    @Override
    public CaliberDto getLastEntry() {
        return null;
    }

    /**
     * Retourne la liste complete de T actif ou non
     *
     * @return List<T>
     */
    @Override
    public List<CaliberDto> getAll() {
        List<Caliber> caliberList = (List<Caliber>) this.caliberRepository.findAll();
        return ModelMapperTool.mapList(caliberList, CaliberDto.class);
    }

    @Override
    public List<CaliberDto> getAllActive() {
        return this.getAll();
    }

    @Override
    public CaliberDto create(Caliber newObjectDto) {
        return null;
    }

    @Override
    public CaliberDto update(CaliberDto updateObjectDto) {
        return null;
    }

    @Override
    public List<CaliberDto> delete(CaliberDto deleteObjectDto) {
        return null;
    }
}
