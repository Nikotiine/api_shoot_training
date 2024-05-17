package fr.nicolas.godin.shoot_training_api.api.tools;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class ModelMapperTool {


    /**
     * Map une liste d'objet
     * @param initialList la liste d'entrée
     * @param dtoClass la classe dans la quelle elle doit etre transformée
     * @return une liste dans la classe passée en param
     * @param <T> Liste d'objet a transformer
     * @param <D> .class de la classe attendue
     */
    public static <T, D> List<D> mapList(List<T> initialList, Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        Function<T, D> mapFunction = entity -> modelMapper.map(entity, dtoClass);
        return initialList.stream().map(mapFunction).toList();
    }

    /**
     * Map un objet
     * @param initialObject objet initial
     * @param dtoClass la classe dans le quel il doit etre transformée
     * @return l'objet transfomé
     * @param <T> objet nitial
     * @param <D> .class de la classe attendue
     */
    public static <T, D> D mapDto(T initialObject,Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(initialObject, dtoClass);
    }

}
